package com.example.admin.msensorplot;

/**
 * Created by admin on 2016/11/13.
 */

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.ClickableArea;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class ChartService2 {

    private GraphicalView mGraphicalView;
    private XYMultipleSeriesDataset multipleSeriesDataset;// 数据集容器
    private XYMultipleSeriesRenderer multipleSeriesRenderer;// 渲染器容器
    private XYSeries mSeries1,mSeries2,mSeries3,mSeries4;// 单条曲线数据集
    //private XYSeriesRenderer mRenderer;// 单条曲线渲染器
    private Context context;

    public ChartService2(Context context) {
        this.context = context;
    }

    /**
     * 获取图表
     *
     * @return
     */
    public GraphicalView getGraphicalView() {
        mGraphicalView = ChartFactory.getCubeLineChartView(context,
                multipleSeriesDataset, multipleSeriesRenderer, 0.1f);
        mGraphicalView.repaint();
        return mGraphicalView;
    }

    /**
     * 获取数据集，及xy坐标的集合
     *
     * @param c0
     */
    public void setXYMultipleSeriesDataset(String c0, String c1, String c2) {
        multipleSeriesDataset = new XYMultipleSeriesDataset();
        /*mSeries1 = new XYSeries("acc");
        mSeries2 = new XYSeries("x");
        mSeries3 = new XYSeries("y");
        mSeries4 = new XYSeries("z");*/
        mSeries1 = new TimeSeries(c0);
        mSeries2 = new TimeSeries(c1);
        mSeries3 = new TimeSeries(c2);
        multipleSeriesDataset.addSeries(mSeries1);
        multipleSeriesDataset.addSeries(mSeries2);
        multipleSeriesDataset.addSeries(mSeries3);
        //multipleSeriesDataset.addSeries(mSeries4);
    }

    /**
     * 获取渲染器
     *
     * @param minX
     *            x轴最小值
     * @param maxX
     *            x轴最大值
     * @param minY
     *            y轴最小值
     * @param maxY
     *            y轴最大值
     * @param chartTitle
     *            曲线的标题
     * @param xTitle
     *            x轴标题
     * @param yTitle
     *            y轴标题
     * @param axeColor
     *            坐标轴颜色
     * @param labelColor
     *            标题颜色
    //    * @param curveColor
     *            曲线颜色
     * @param gridColor
     *            网格颜色
     */
    public void setXYMultipleSeriesRenderer(double minX, double maxX, double minY, double maxY,
                                            String chartTitle, String xTitle, String yTitle, int axeColor,
                                            int labelColor, /*int curveColor,*/ int gridColor) {
        multipleSeriesRenderer = new XYMultipleSeriesRenderer();
        if (chartTitle != null) {
            multipleSeriesRenderer.setChartTitle(chartTitle);
        }
        multipleSeriesRenderer.setXTitle(xTitle);
        multipleSeriesRenderer.setYTitle(yTitle);
        multipleSeriesRenderer.setRange(new double[] { minX, maxX, minY, maxY });//xy轴的范围
        multipleSeriesRenderer.setLabelsColor(labelColor);
        multipleSeriesRenderer.setXLabels(10);
        multipleSeriesRenderer.setYLabels(10);
        multipleSeriesRenderer.setXLabelsAlign(Paint.Align.RIGHT);
        multipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        multipleSeriesRenderer.setAxisTitleTextSize(20);
        multipleSeriesRenderer.setChartTitleTextSize(20);
        multipleSeriesRenderer.setLabelsTextSize(20);
        multipleSeriesRenderer.setLegendTextSize(20);
        multipleSeriesRenderer.setPointSize(2f);//曲线描点尺寸
        multipleSeriesRenderer.setFitLegend(true);
        //multipleSeriesRenderer.setDisplayChartValues(true) ;
        multipleSeriesRenderer.setMargins(new int[] { 20, 30, 15, 20 });
        multipleSeriesRenderer.setShowGrid(true);
        multipleSeriesRenderer.setZoomEnabled(false, false);
        multipleSeriesRenderer.setAxesColor(axeColor);
        multipleSeriesRenderer.setGridColor(gridColor);
        multipleSeriesRenderer.setBackgroundColor(Color.BLACK);//背景色
        //multipleSeriesRenderer.setGridColor(Color.BLACK);
        multipleSeriesRenderer.setMarginsColor(Color.BLACK);//边距背景色，默认背景色为黑色，这里修改为白色
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.BLUE);//第一条
        r.setLineWidth(2f);
        r.setPointStyle(PointStyle.CIRCLE);
        multipleSeriesRenderer.addSeriesRenderer(r);
        r = new XYSeriesRenderer();
        r.setColor(Color.RED);//第二条
        r.setLineWidth(2f);
        r.setPointStyle(PointStyle.CIRCLE);
        multipleSeriesRenderer.addSeriesRenderer(r);
        r = new XYSeriesRenderer();
        r.setColor(Color.GREEN);//第三条
        r.setLineWidth(2f);
        r.setPointStyle(PointStyle.CIRCLE);
        multipleSeriesRenderer.addSeriesRenderer(r);
    /*    r = new XYSeriesRenderer();
        r.setColor(Color.BLACK);//第四条
        r.setPointStyle(PointStyle.CIRCLE);
        multipleSeriesRenderer.addSeriesRenderer(r);
        /*mRenderer = new XYSeriesRenderer();
        mRenderer.setColor(curveColor);
        mRenderer.setPointStyle(PointStyle.CIRCLE);//描点风格，可以为圆点，方形点等等
        multipleSeriesRenderer.addSeriesRenderer(mRenderer);*/
    }

    /**
     * 根据新加的数据，更新曲线，只能运行在主线程
     *
     * @param x
     *            新加点的x坐标
     * @param y
     *            新加点的y坐标
     * @param z
     *
     * @param t
     */
    public void updateChart(double t, double x, double y, double z) {
        mSeries1.add(t, x);
        mSeries2.add(t, y);
        mSeries3.add(t, z);
        mGraphicalView.invalidate();;//此处也可以调用invalidate()
    }
   /* public void updateChart(double t, double x) {
        multipleSeriesDataset.removeSeries(mSeries1);
        //multipleSeriesDataset.removeSeries(mSeries4);
        mSeries1.add(t, x);
        multipleSeriesDataset.addSeries(mSeries1);
        //multipleSeriesDataset.addSeries(mSeries4);
        mGraphicalView.invalidate();;//此处也可以调用invalidate()
    }

    /**
     * 添加新的数据，多组，更新曲线，只能运行在主线程
     * @param xList
     * @param yList
     */
   /* public void updateChart(List<Double> xList, List<Double> yList) {
        multipleSeriesDataset.removeSeries(mSeries1);
        for (int i = 0; i < xList.size(); i++) {
            mSeries1.add(xList.get(i), yList.get(i));
        }
        multipleSeriesDataset.addSeries(mSeries1);
        mGraphicalView.invalidate();;//此处也可以调用invalidate()
    }*/
}

