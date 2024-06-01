package wtwt.common.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ScrollResponse<T>(
    List<T> contents,
    boolean isEmpty,
    String nextCursor,
    int scrollSize
) {

}
