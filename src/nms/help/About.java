package nms.help;

import nms.main.MainActivity;
import nms.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ben on 7/6/2015.
 */
public class About extends Activity {
	private Button nb1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		nb1 = (Button) findViewById(R.id.dismiss);
		nb1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(About.this, MainActivity.class);
				startActivity(myIntent);
			}
		});
	}

}
