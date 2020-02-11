package com.andrei.beerservice.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(Timestamp ts) {
//        if(Objects.nonNull(ts)){
//            return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
//                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(),
//                    ts.toLocalDateTime().getMinute(), ts.toLocalDateTime().getSecond(),
//                    ts.toLocalDateTime().getNano(), ZoneOffset.UTC);
//        } else {
        return null;
//        }
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime){
        if(Objects.nonNull(offsetDateTime)){
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else{
            return null;
        }
    }
}
