package codemonkey.projecttracker;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

import codemonkey.projecttracker.dummy.DummyContent;

/**
 * A fragment representing a single Project detail screen.
 * This fragment is either contained in a {@link ProjectListActivity}
 * in two-pane mode (on tablets) or a {@link ProjectDetailActivity}
 * on handsets.
 */
public class ProjectDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProjectDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_project_detail, container, false);
        View rootView = inflater.inflate(R.layout.project_detail_layout, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //((TextView) rootView.findViewById(R.id.project_detail)).setText(mItem.content);
            ((TextView) rootView.findViewById(R.id.textView)).setText(mItem.content);
            ((TextView) rootView.findViewById(R.id.textView2)).setText(mItem.detailContent);

            boolean wtfIsThis = ActivityManager.isUserAMonkey();

            final Activity activity = this.getActivity();

            if (mItem.id.equals("4")) {
                final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.test_image);

                Timer timer = new Timer();
                TimerTask spinningTask = new TimerTask() {
                    private Runnable spinner = new Runnable() {

                        @Override
                        public void run() {
                            imageView.setRotation(imageView.getRotation() + 5f);
                        }
                    };

                    @Override
                    public void run() {
                        activity.runOnUiThread(spinner);
                    }
                };

                timer.scheduleAtFixedRate(spinningTask, 0, 25);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageView imageView = (ImageView)view;
                        imageView.setImageAlpha((int) (Math.random() * 255));
                    }
                });
                imageView.setClickable(true);
            }
        }

        return rootView;
    }
}
