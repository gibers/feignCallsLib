package com.oidccall.feigncallslib.feignCalls;

import com.oidccall.dtos.feign.ListResponseUsersDto;
import com.oidccall.feigncallslib.TokenFromAuth0;
import com.oidccall.feigncallslib.feignInterfaces.GetUsersWithFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiV2GetUsersRequest {

  private final GetUsersWithFeign getUsersWithFeign;
  private final TokenFromAuth0 tokenFromAuth0;

  public ListResponseUsersDto getListUsersNeverLoggedIn(LocalDate limitDate, int page) {
    String qParams = generateQParamsNeverLoggedIn(limitDate);
    return this.getUsersWithFeign.getUsersNeverLoggedIn(
      "Bearer " + this.tokenFromAuth0.getFullToken().getAccess_token(),
      null, page, true, null, qParams);
  }

  private String generateQParamsNeverLoggedIn(LocalDate limitDate) {
    TermQuery loginsCountQuery = new TermQuery(new Term("logins_count", "0"));
    String stringDate = limitDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    TermRangeQuery createdAt = TermRangeQuery.newStringRange("created_at", null, stringDate, true, true);

    BooleanQuery.Builder builder = new BooleanQuery.Builder();
    builder.add(loginsCountQuery, BooleanClause.Occur.MUST);
    builder.add(createdAt, BooleanClause.Occur.MUST);
    BooleanQuery finalQuery = builder.build();
    return finalQuery.toString();
  }

}
