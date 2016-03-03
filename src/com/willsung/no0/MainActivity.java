package com.willsung.no0;

import android.os.Bundle;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity{
	
	private DevicePolicyManager policyManager;
	private ComponentName componentName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        policyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
		componentName = new ComponentName(this, AdminReceiver.class); 
		//调用锁屏
		lock();
		//干掉进程
		android.os.Process.killProcess(android.os.Process.myPid()); 
	}
	
	//锁屏
	private void lock(){   
		enManage();   
		policyManager.lockNow();
	}  
	
	//获取权限
	private void enManage(){
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
		startActivityForResult(intent, 0); 
	}
}