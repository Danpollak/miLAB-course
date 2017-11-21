package pollak.dan.exercise_04;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class sendNotification extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SEND_NOTIFICATION = "pollak.dan.exercise_04.action.SEND_NOTIFICATION";
    public sendNotification() {
        super("sendNotification");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startSendNotification(Context context, String interval) {
        Intent intent = new Intent(context, sendNotification.class);
        intent.setAction(ACTION_SEND_NOTIFICATION);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), TimeUnit.MINUTES.toMillis(Integer.parseInt(interval)), PendingIntent.getService(context,0,intent, 0));

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SEND_NOTIFICATION.equals(action)) {
                handleActionSendNotification();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionSendNotification() {
        String quote = getQuote();
        NotificationManager notificationManager = (NotificationManager)getSystemService(Service.NOTIFICATION_SERVICE);
        Notification.Builder notificationBuilder = new Notification.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.dead_droid_round);
        notificationBuilder.setContentTitle("Geek Quote");
        notificationBuilder.setContentText(quote);
        notificationManager.notify(1337, notificationBuilder.build());
    }

    private String getQuote() {
        String[] quoteBank = {"Computer science is no more about computers than astronomy is about telescopes.\n Edsger Dijkstra",
                "Simple things should be simple, complex things should be possible. \n Alan Kay",
                "Never trust a computer you can’t throw out a window. \n Steve Wozniak",
                "The city’s central computer told you?  R2D2, you know better than to trust a strange computer! \n C3PO",
                "Hardware: The parts of a computer system that can be kicked. \n Jeff Pesis",
                "I’ve finally learned what ‘upward compatible’ means.  It means we get to keep all our old mistakes. \n Dennie van Tassel",
                "There are two major products that come out of Berkeley: LSD and UNIX.  We don’t believe this to be a coincidence. \n Jeremy S. Anderson",
                "Any fool can use a computer.  Many do. \n Ted Nelson ",
                "There are only two industries that refer to their customers as ‘users’. \n Edward Tufte",
                "Don’t worry if it doesn’t work right. If everything did, you’d be out of a job.",
                "Measuring programming progress by lines of code is like measuring aircraft building progress by weight. \n Bill Gates",
                "The best thing about a boolean is even if you are wrong, you are only off by a bit.",
                "Optimism is an occupational hazard of programming; feedback is the treatment. \n Kent Beck",
                "To iterate is human, to recurse divine. \n L. Peter Deutsch",
                "If Java had true garbage collection, most programs would delete themselves upon execution. \n Robert Sewell"
};
        Random r = new Random();
        return quoteBank[r.nextInt((4-0)+1)];

    }

}
