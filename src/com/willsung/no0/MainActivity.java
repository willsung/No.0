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
		//��������
		lock();
		//�ɵ�����
		android.os.Process.killProcess(android.os.Process.myPid()); 
	}
	
	//����
	private void lock(){   
		enManage();   
		policyManager.lockNow();
	}  
	
	//��ȡȨ��
	private void enManage(){
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
		startActivityForResult(intent, 0); 
	}
}