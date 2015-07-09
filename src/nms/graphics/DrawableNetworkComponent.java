package nms.graphics;

import nms.main.R;
import android.content.Context;

public class DrawableNetworkComponent extends Drawable {
	public enum Type {
		ROUTER, USER, UNKNOWN
	}

	private Type mType;

	public DrawableNetworkComponent(Context context, Type type) {
		super(100, 40);
		this.mType = type;
		switch (mType) {
		case ROUTER:
			loadImage(context, R.drawable.ic_router);
			break;
		case USER:
			loadImage(context, R.drawable.ic_node);
			break;
		default:
			loadImage(context, R.drawable.ic_launcher);
			break;
		}
	}
}
