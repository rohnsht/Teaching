package np.com.rohanshrestha.firstproject.services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by legen on 14/11/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        sendRegistrationTokenToServer(refreshedToken);
    }

    private void sendRegistrationTokenToServer(String refreshedToken) {
        // write code to send token to your server
    }
}
