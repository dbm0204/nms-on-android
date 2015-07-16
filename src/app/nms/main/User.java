package app.nms.main;
import android.content.Context;
import app.nms.graphics.DrawableNetworkComponent;
import app.nms.graphics.DrawableNetworkComponent.Type;

public class User extends DrawableNetworkComponent
{
	public User(Context context) {
		super(context, Type.USER);
	}

}
