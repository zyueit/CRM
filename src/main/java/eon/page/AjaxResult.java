package eon.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxResult {
    private boolean success;
    private String msg;

    public AjaxResult(String msg) {
        this(false, msg);
    }

    public AjaxResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
