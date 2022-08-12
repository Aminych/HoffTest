package org.o7planning.hofftest.network;

import org.o7planning.hofftest.model.Constructor;
import org.o7planning.hofftest.model.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3")
        // category_id=320
        // sort_by=popular
        // sort_type=desc
        // limit=40
        // offset=0
        // device_id=3a7612bcc84813b5
        // isAndroid=true
        // app_version=1.8.16
        // location=19
        // xhoff=36f40b3d665cdb9435904796172dde5e3f13aa8a%3A4630

    Call<List<Models>> getCatalog(
//            @Query("category_id") String categoryId,
//            @Query("sort_by") String sort_by,
//            @Query("sort_type") String sort_type,
//            @Query("limit") String limit,
//            @Query("offset") String offset
//                                 @Query("device_id") String device_id,
//                                 @Query("isAndroid") String isAndroid,
//                                 @Query("app_version") String app_version,
//                                 @Query("location") String location,
//                                 @Query("xhoff") String xhoff
    );
}