package eon.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
    private int page = 1;
    private int rows = 5;

    public int getStartIndex() {
        return (page - 1) * rows;
    }
}
