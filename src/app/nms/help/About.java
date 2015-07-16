package app.nms.help;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import app.nms.R;

/**
 * Created by Ben on 7/6/2015.
 */

public class About extends Activity implements OnClickListener{
    Button btnOpenPopup;
    Button btnDismiss;
    View popupView;
    PopupWindow popupWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpenPopup = (Button) findViewById(R.id.about);
        btnOpenPopup.setOnClickListener((Button.OnClickListener) this);
        btnDismiss = (Button) findViewById(R.id.dismiss);
        btnDismiss.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
    }

    @Override
    public void onClick(View arg0) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.about, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                                                  LayoutParams.WRAP_CONTENT,
                                                  LayoutParams.WRAP_CONTENT);
    }
}