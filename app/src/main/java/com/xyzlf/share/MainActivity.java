package com.xyzlf.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xyzlf.share.library.bean.ShareEntity;
import com.xyzlf.share.library.interfaces.ShareConstant;
import com.xyzlf.share.library.util.ShareUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ShareEntity testBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testBean = new ShareEntity("我是标题", "我是内容，描述内容。");
        testBean.setUrl("https://www.baidu.com"); //分享链接
        testBean.setImgUrl("https://www.baidu.com/img/bd_logo1.png");

        findViewById(R.id.share_qq).setOnClickListener(this);
        findViewById(R.id.share_qzone).setOnClickListener(this);
        findViewById(R.id.share_weibo).setOnClickListener(this);
        findViewById(R.id.share_weixin).setOnClickListener(this);
        findViewById(R.id.share_wxcircle).setOnClickListener(this);

    }

    /**
     * 使用统一数据结构
     */
    public void showShareDialog(View view) {
        ShareEntity testBean = new ShareEntity("我是标题", "我是内容，描述内容。");
        testBean.setUrl("https://www.baidu.com"); //分享链接
        testBean.setImgUrl("https://www.baidu.com/img/bd_logo1.png");
        ShareUtil.showShareDialog(this, testBean, ShareConstant.REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 分享回调处理
         */
        if (requestCode == ShareConstant.REQUEST_CODE) {
            if (data != null) {
                int channel = data.getIntExtra(ShareConstant.EXTRA_SHARE_CHANNEL, -1);
                int status = data.getIntExtra(ShareConstant.EXTRA_SHARE_STATUS, -1);
                onShareCallback(channel, status);
            }
        }
    }

    /**
     * 分享回调处理
     * @param channel
     * @param status
     */
    private void onShareCallback(int channel, int status) {
        new ShareCallBack().onShareCallback(channel, status);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.share_qq:
                ShareUtil.startShare(this, ShareConstant.SHARE_CHANNEL_QQ, testBean, ShareConstant.REQUEST_CODE);
                break;
            case R.id.share_qzone:
                ShareUtil.startShare(this, ShareConstant.SHARE_CHANNEL_QZONE, testBean, ShareConstant.REQUEST_CODE);
                break;
            case R.id.share_weibo:
                ShareUtil.startShare(this, ShareConstant.SHARE_CHANNEL_SINA_WEIBO, testBean, ShareConstant.REQUEST_CODE);
                break;
            case R.id.share_weixin:
                ShareUtil.startShare(this, ShareConstant.SHARE_CHANNEL_WEIXIN_FRIEND, testBean, ShareConstant.REQUEST_CODE);
                break;
            case R.id.share_wxcircle:
                ShareUtil.startShare(this, ShareConstant.SHARE_CHANNEL_WEIXIN_CIRCLE, testBean, ShareConstant.REQUEST_CODE);
                break;
        }
    }
}
