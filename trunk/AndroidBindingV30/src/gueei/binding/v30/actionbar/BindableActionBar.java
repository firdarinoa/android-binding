package gueei.binding.v30.actionbar;

import gueei.binding.BindingLog;
import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.app.ActionBar;
import android.app.Activity;
import android.view.View;

public class BindableActionBar extends View implements IBindableView<BindableActionBar>{

	Activity mActivity;
	
	public ActionBar getActionBar(){
		return mActivity.getActionBar();
	}
	
	public BindableActionBar(Activity context) {
		super(context);
		mActivity = context;
	}

	public ViewAttribute<? extends View, ?> createViewAttribute(
			String attributeId) {
		try{
			String capId = attributeId.substring(0, 1).toUpperCase() + attributeId.substring(1);
			String className = "gueei.binding.v30.actionbar.attributes." + capId;
			return (ViewAttribute<?,?>)Class.forName(className)
						.getConstructor(BindableActionBar.class)
						.newInstance((BindableActionBar)this);
		}catch(Exception e){
			BindingLog.warning("ActionBarAttributeBinder", "Attribute not found");
			return null;
		}
	}
}
