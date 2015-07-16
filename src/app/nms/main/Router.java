package app.nms.main;

import android.content.Context;
import app.nms.graphics.DrawableNetworkComponent;
import app.nms.graphics.DrawableNetworkComponent.Type;

public class Router extends DrawableNetworkComponent {
	public Router(Context context) {
		super(context, Type.ROUTER);
	}

}
