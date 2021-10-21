package com.medicalshop.app.ui.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class PageRow {
    private Canvas canvas ;
    private int startX;
    private int startY;
    private int width;
    private int height;
    private int noOfColumns;
    private Rect rect;
    private List<Rect> columns;
    private List<Float> columnWidth;
    public PageRow(Canvas canvas,int startX,int startY,int width,int height)
    {
        this.canvas=canvas;
        this.startX=startX;
        this.startY=startY;
        this.width=width;
        this.height=height;
        noOfColumns=1;
        rect=new Rect(startX,startY,startX+width,startY+height);
        columnWidth=new ArrayList<Float>();
        columns=new ArrayList<Rect>();
        columnWidth.add(new Float(1.0));
        columns.add(new Rect(rect.left,rect.right,(int)(rect.width()*columnWidth.get(0).floatValue()),rect.height()));
    }
    public void showCol(int colN, String text, Paint brush)
    {
        canvas.drawText(text,columns.get(colN).left,columns.get(colN).bottom,brush);
    }

}
