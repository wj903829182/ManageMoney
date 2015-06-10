package com.jack.managemoney;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class VoiceActivity extends Activity implements OnClickListener {

	private TextView t_hint;// 提示
	private EditText et_voice;// 文本编辑
	private ImageButton imgbtOk;// 输入确定按钮
	private Button btvoice;// 点击进行语音输入
	private ImageButton imgbtBack;// 返回
	private String str = "使用智能语音记账功能会产生网络流量, 请按住下方语音按钮使用本功能";
	private final int VOICE_RECOGNITION_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 无标题
		// 全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 强制竖屏
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.voice);
		// 调用初始化函数
		initialise();
		// 设置监听
		setListen();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.img_voice_ok:
			break;

		case R.id.touch_voice:
			handleVoiceInput();
			break;

		case R.id.img_voice_back:
			finish();
			break;
		}
	}

	// 初始化函数
	private void initialise() {
		// 初始化组件
		t_hint = (TextView) findViewById(R.id.input_text_hint);
		et_voice = (EditText) findViewById(R.id.voice_input_text);
		imgbtOk = (ImageButton) findViewById(R.id.img_voice_ok);
		btvoice = (Button) findViewById(R.id.touch_voice);
		imgbtBack = (ImageButton) findViewById(R.id.img_voice_back);

		// 初始化参数设置
		et_voice.setCursorVisible(false);// 设置文本编辑框是否显示光标

		// 添加图片主要用SpannableString和ImageSpan类
		Drawable drawable = getResources().getDrawable(
				R.drawable.yy_voice_warning);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		// 需要处理的文本，[smile]是需要被替代的文本
		SpannableString spannable = new SpannableString("[warning]" + str);
		// 要让图片替代指定的文字就要用ImageSpan
		ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
		// 开始替换，注意第2和第3个参数表示从哪里开始替换到哪里替换结束（start和end）
		// 最后一个参数类似数学中的集合,[5,12)表示从5到12，包括5但不包括12
		spannable.setSpan(span, 0, "[warning]".length(),
				Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		et_voice.setTextColor(getResources().getColor(R.color.warn));
		et_voice.setTextSize(15);
		et_voice.setText(spannable);
	}

	// 为组件设计监听
	private void setListen() {
		// 设置监听
		imgbtOk.setOnClickListener(this);
		btvoice.setOnClickListener(this);
		imgbtBack.setOnClickListener(this);

	}

	private void handleVoiceInput() {
		PackageManager pm = getPackageManager();// /判断当前手机是否支持语音识别功能
		//Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		Intent intent =new Intent(RecognizerIntent.ACTION_WEB_SEARCH); // 网络识别程序
		  List<ResolveInfo> list=pm.queryIntentActivities( intent, 0);
		 if(list.size()!=0){
			 Toast.makeText(VoiceActivity.this,
					 
		 "当前语音设备可用...", Toast.LENGTH_SHORT).show(); 
			 }else{
		 //btvoice.setEnabled(false); 
			 Toast.makeText(VoiceActivity.this,
		 "当前语音设备不可用，不能进行语音识别...", Toast.LENGTH_SHORT).show(); }
		 
		/*try {
			// 语言模式和自由形式的语音识别
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
					RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			// 提示语言开始
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "请开始语音");
			// 开始语音识别
			startActivityForResult(intent, 1);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(VoiceActivity.this, "找不到语音设备", Toast.LENGTH_LONG)
					.show();

		}*/

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE
				&& resultCode == RESULT_OK) {

			// 取得语音的字符
			ArrayList<String> results = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			// 设置视图的更新
			/*
			 * mList.setAdapter(new ArrayAdapter<String>(this,
			 * android.R.layout.simple_list_item_1, results)); String
			 * resultsString = ""; for (int i = 0; i < results.size(); i++) {
			 * resultsString += results.get(i); }
			 */
			Toast.makeText(VoiceActivity.this, "the results=" + results.get(0),
					Toast.LENGTH_LONG).show();

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
