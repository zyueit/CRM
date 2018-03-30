package eon.service.impl;

import com.alibaba.fastjson.JSON;
import eon.domain.Attence;
import eon.domain.Employee;
import eon.mapper.AttenceMapper;
import eon.page.PageResult;
import eon.query.AttenceQueryObject;
import eon.service.IAttenceService;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

@Service
public class AttenceService implements IAttenceService {
    private static String GET_PREFIX = "get";
    @Autowired
    private AttenceMapper attenceMapper;

    @Override
    public void save(Attence attence) {
        attenceMapper.save(attence);
    }

    @Override
    public Attence get(Long id) {
        return attenceMapper.get(id);
    }

    @Override
    public PageResult<Attence> query(AttenceQueryObject qo) {
        int totalCount = attenceMapper.count(qo).intValue();
        if (totalCount == 0) {
            return PageResult.empty();
        }
        List<Attence> result = attenceMapper.list(qo);
        return new PageResult<Attence>(totalCount, result);
    }

    @Override
    public void delete(Long id) {
        attenceMapper.delete(id);
    }

    @Override
    public void update(Attence attence) {
        attenceMapper.update(attence);
    }


    //获得前台数据并且转化为临时文件供用户下载
    public void down(HttpServletRequest request, HttpServletResponse response, String data) {
        WritableWorkbook workbook = null;
        File file = null;
        List<Attence> attences = JSON.parseArray(data, Attence.class);
        Class type = Attence.class;
        Field[] declaredFields = type.getDeclaredFields();
        //检查是否存在temp文件夹,用于临时存放
        String contextPath = request.getServletContext().getRealPath("/WEB-INF/temp");
        File temp = new File(contextPath);
        if (!temp.exists()) {
            temp.mkdirs();
        }
        try {
            file = new File(contextPath, "/" + UUID.randomUUID() + ".xls");
            workbook = Workbook.createWorkbook(file);
//        //生成页
            WritableSheet sheet = workbook.createSheet("第一页", 0);
            //绘制第一行头
            for (int i = 0; i < declaredFields.length; i++) {
                sheet.addCell(new Label(i, 0, declaredFields[i].getName()));
            }
            for (int i = 0; i < attences.size(); i++) {
                Attence attence = attences.get(i);
                Label label = null;
                for (int j = 0; j < declaredFields.length; j++) {
//                    //设置字体为Arial，30号，加粗
                    CellView cv = new CellView();
                    cv.setAutosize(true);
                    sheet.setColumnView(i + 1, cv);
                    Field declaredField = declaredFields[j];
                    String field = declaredField.getName();
                    Method method = type.getDeclaredMethod(GET_PREFIX + field.substring(0, 1).toUpperCase() + field.substring(1));
                    if (method.getName().indexOf("User") != -1) {
                        int index = 1;
                        //当前字段为User
                        Employee addSignUser = attence.getAddSignUser();//进来第一次执行对象
                        Employee user = attence.getUser();//第二次进来执行对象
                        Method getUsername = addSignUser.getClass().getDeclaredMethod("getUsername");
                        if (index++ == 1) {
                            label = new Label(j, i + 1, (String) getUsername.invoke(addSignUser));
                        } else if (index == 2) {
                            label = new Label(j, i + 1, (String) getUsername.invoke(addSignUser));
                        }
                        sheet.addCell(label);
                    } else {
                        //执行普通字段get
                        String s = method.invoke(attence).toString();
                        label = new Label(j, i + 1, s);
                        sheet.addCell(label);
                    }
                }
            }
            workbook.write();
            //    下载
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //工作流不为空时关闭,进行下载转换
                if (workbook != null) {
                    workbook.close();
                    try (InputStream in = new FileInputStream(file);
                         OutputStream out = response.getOutputStream();) {
                        //设置相应类型application/octet-stream
                        response.setContentType("application/x-msdownload");
                        //设置头信息                 Content-Disposition为属性名  附件形式打开下载文件   指定名称  为 设定的filename
                        response.setHeader("Content-Disposition", "attachment;filename=\"Attence.xls");
                        byte[] datas = new byte[in.available()];
                        in.read(datas);
                        out.write(datas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        //删除临时文件
                        file.delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
