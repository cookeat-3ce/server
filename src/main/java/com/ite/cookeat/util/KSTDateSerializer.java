package com.ite.cookeat.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date 데이터를 KST로 변환하는 클래스
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.24
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.24    김지수       최초 생성
 * </pre>
 */
public class KSTDateSerializer extends StdSerializer<Date> {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  static {
    dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
  }

  public KSTDateSerializer() {
    this(null);
  }

  public KSTDateSerializer(Class<Date> t) {
    super(t);
  }

  @Override
  public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    String formattedDate = dateFormat.format(date);
    gen.writeString(formattedDate);
  }
}