package eon.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eon.vo.PotentialChartVO;
import eon.vo.PotentialVO;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ChartUtil {
    public static Map<String, Object> dat(HttpServletRequest request, List<PotentialVO> query) throws JsonProcessingException {
        Map<String, List> map = new HashMap<>();
        for (PotentialVO vo : query) {
            String time = vo.getInputTime();
            if (!map.keySet().contains(time)) {
                List<PotentialVO> y = new ArrayList<>();
                y.add(vo);
                map.put(vo.getInputTime(), y);
            } else {
                map.get(vo.getInputTime()).add(vo);
            }
        }
        //所有查出来的员工
        List<PotentialVO> ys = new ArrayList<>();
        for (PotentialVO potentialVO : query) {
            if (ys.size() == 0) {
                ys.add(potentialVO);
            } else {
                boolean add = true;
                for (PotentialVO y : ys) {
                    if (potentialVO.getUsername().equals(y.getUsername())) {
                        add = false;
                    }
                }
                if (add == true) {
                    ys.add(potentialVO);
                }
            }
        }
        Set<String> strings = map.keySet();
        PotentialVO v = null;
        for (String s : strings) {
            List<PotentialVO> list = map.get(s);
            //取出当前含有的用户组,循环ys,判断用户组中是否有这个用户,有的话存入count,反之为0
            for (PotentialVO y : ys) {
                boolean has = false;//不含有
                for (PotentialVO vo : list) {
                    if (y.getUsername().equals(vo.getUsername())) {
                        has = true;
                        v = vo;
                        break;
                    }
                }
                //如果有添加
                if (has == true) {
                    y.getData().add(v.getCustomerCount());
                } else {
                    y.getData().add(0l);
                }
            }
        }
        List<PotentialChartVO> result = new ArrayList<>();
        for (PotentialVO vo : ys) {
            result.add(new PotentialChartVO(vo.getUsername(), vo.getData()));
        }
        HashMap<String, Object> mymap = new HashMap<>();
        mymap.put("data", result);
        mymap.put("y", strings);
        return mymap;
//        ObjectMapper ow = new ObjectMapper();
//        request.setAttribute("data", ow.writeValueAsString(result));
//        request.setAttribute("y", ow.writeValueAsString(strings));
    }
}
