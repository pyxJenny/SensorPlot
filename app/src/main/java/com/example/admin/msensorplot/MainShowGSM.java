package com.example.admin.msensorplot;

/**
 * Created by admin on 2016/11/29.
 */
        import java.util.List;

        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;
        import android.telephony.NeighboringCellInfo;
        import android.telephony.TelephonyManager;
        import android.telephony.cdma.CdmaCellLocation;
        import android.telephony.gsm.GsmCellLocation;
        import android.util.Log;
        import android.view.View;
        import android.widget.TextView;

/**
 * 功能描述：通过手机信号获取基站信息
 * # 通过TelephonyManager 获取lac:mcc:mnc:cell-id
 * # MCC，Mobile Country Code，移动国家代码（中国的为460）；
 * # MNC，Mobile Network Code，移动网络号码（中国移动为0，中国联通为1，中国电信为2）；
 * # LAC，Location Area Code，位置区域码；
 * # CID，Cell Identity，基站编号；
 * # BSSS，Base station signal strength，基站信号强度。
 * @author android_ls
 */
public class MainShowGSM extends Activity {
    TextView message, NumOfCell;

    //private static final String TAG = "GSMCellLocationActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsm);
        message = (TextView) findViewById(R.id.message);
        NumOfCell = (TextView) findViewById(R.id.NumOfCell);

        // 获取基站信息
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                StringBuffer sb1 = new StringBuffer("获取信息如下：");

                // 返回值MCC + MNC
                String operator = mTelephonyManager.getNetworkOperator();
                int mcc = Integer.parseInt(operator.substring(0, 3));
                int mnc = Integer.parseInt(operator.substring(3));

                // 中国移动和中国联通获取LAC、CID的方式
                GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();
                int lac = location.getLac();
                int cellId = location.getCid();
                sb1.append("MCC = " + mcc + "\nMNC = " + mnc + "\nLAC = " + lac + "\nCID = " + cellId);
                message.setText(sb1.toString());

                //Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);

                // 中国电信获取LAC、CID的方式
                /*CdmaCellLocation location1 = (CdmaCellLocation) mTelephonyManager.getCellLocation();
                lac = location1.getNetworkId();
                cellId = location1.getBaseStationId();
                cellId /= 16;*/

                // 获取邻区基站信息
                List<NeighboringCellInfo> infos = mTelephonyManager.getNeighboringCellInfo();
                StringBuffer sb = new StringBuffer("总数 : " + infos.size() + "\n");
                for (NeighboringCellInfo info1 : infos) { // 根据邻区总数进行循环
                    sb.append(" LAC : " + info1.getLac()); // 取出当前邻区的LAC
                    sb.append(" CID : " + info1.getCid()); // 取出当前邻区的CID
                    sb.append(" BSSS : " + (-113 + 2 * info1.getRssi()) + "\n"); // 获取邻区基站信号强度
                }
                NumOfCell.setText(sb.toString());

                //Log.i(TAG, " 获取邻区基站信息:" + sb.toString());

            }
        });

    }

}

