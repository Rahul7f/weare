package ar.codeslu.plax.notify;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by CodeSlu on 9/4/2018.
 */

public interface FCM {
    @Headers({

            "Content-Type: application/json",
            "Authorization:key=AAAAeIu0q_4:AAAAL07o4ow:APA91bGCOPoa84aVeBQNBQ1vXARTohUz5l22T8woP6JmS5aB7zK9nmoxsL24S_Vt0O4UTK1LxWgSRf-tacUfSDTHwkk-ByrzwzLN6Uk9B07htvQrbxUR7SVYetG3VlDiCBFibPQU2j8p"
    })
    @POST("fcm/send")
    Call<FCMresp> send(@Body Sender body);
}
