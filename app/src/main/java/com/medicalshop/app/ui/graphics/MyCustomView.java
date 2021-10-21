package com.medicalshop.app.ui.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCustomView extends View {
    private ShapeDrawable drawable;
    public MyCustomView(Context context) {
        super(context);
    }
    public MyCustomView(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
    }
    public MyCustomView(Context context,AttributeSet attributeSet,int defStyleAttr)
    {
        super(context,attributeSet,defStyleAttr);
    }
    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        float currentY = 10;
        drawBill(canvas,getWidth(),getHeight(),getBottom());

    }
    public static void drawBill(Canvas canvas,int width,int height)
    {
        float currentY=5;
        Paint drawingBrush =new Paint();
        drawingBrush.setTextSize(70);
        drawingBrush.setStyle(Paint.Style.FILL);
        drawingBrush.setColor(Color.WHITE);
        //Make the Entire canvas white
        canvas.drawPaint(drawingBrush);
        // draw blue circle with anti aliasin turn off

        drawingBrush.setAntiAlias(true);
        drawingBrush.setColor(Color.BLUE);
        drawingBrush.setTextSize(50);


        String s1="CASH-MEMO ";
        String shopName="NEW PARUL MEDICAL HALL";
        String address="Amas(Gaya)";
        String gst="GSTIN : XXXXXXXXXX12345";
        String date="Date :";
        String dl="No. 134/134A";
        String sl="2021-2022 S.No.";
        String preName="Prescribed to Mr./Mrs.";
        String preBy="Prescribed by Dr.";

        int leftMargin=10,topMargin=10,rightMargin=10,bottomMargin=10,lineTopMargin=10;


        Rect cashMemoDim=new Rect();
        drawingBrush.getTextBounds(s1,0,s1.length(),cashMemoDim);

        Rect shopNameDim=new Rect();
        drawingBrush.getTextBounds(shopName,0,shopName.length(),shopNameDim);

        Rect addressDim=new Rect();
        drawingBrush.getTextBounds(address,0,address.length(),addressDim);

        Rect dlNoDim= new Rect();
        drawingBrush.getTextBounds(dl,0,dl.length(),dlNoDim);

        Rect slNoDim=new Rect();
        drawingBrush.getTextBounds(sl,0,sl.length(),slNoDim);

        Rect dateDim= new Rect();
        drawingBrush.setStyle(Paint.Style.STROKE);
        drawingBrush.getTextBounds(date,0,date.length(),dateDim);

        Rect gstDim=new Rect();
        drawingBrush.getTextBounds(gst,0,gst.length(),gstDim);

        Rect preNameDim=new Rect();
        drawingBrush.getTextBounds(preName,0,preName.length(),preNameDim);

        Rect preByDim=new Rect();
        drawingBrush.getTextBounds(preBy,0,preBy.length(),preByDim);


        Rect l1 =new Rect(leftMargin,(int)currentY+lineTopMargin,width-rightMargin,(int)currentY+lineTopMargin+cashMemoDim.height());


        Rect l2=new Rect(l1.left,l1.top+l1.height()+lineTopMargin,l1.right,l1.top+l1.height()+lineTopMargin+shopNameDim.height());

        Rect sadd=new Rect(l2.left,l2.top+l2.height()+lineTopMargin,l2.right,l2.top+l2.height()+addressDim.height());

        // dl sl
        Rect dlSl= new Rect(sadd.left,sadd.top+sadd.height()+lineTopMargin,sadd.right,sadd.top+sadd.height()+lineTopMargin+5+dlNoDim.height());

        Rect l3=new Rect(dlSl.left,dlSl.top+dlSl.height()+lineTopMargin,dlSl.right,dlSl.top+dlSl.height()+lineTopMargin+gstDim.height());

        Rect l6=new Rect(l3.left,l3.top+l3.height()+lineTopMargin,l3.right,l3.top+l3.height()+lineTopMargin+preNameDim.height());
        Rect l7= new Rect(l6.left,l6.top+l6.height()+lineTopMargin,l6.right,l6.top+l6.height()+lineTopMargin+preNameDim.height());

        Rect border =new Rect(l7.left,l7.top+l7.height()+lineTopMargin,l7.right,height-bottomMargin);
        canvas.drawRect(border,drawingBrush);



        drawingBrush.setStyle(Paint.Style.FILL);

        canvas.drawText(s1,width/2-cashMemoDim.width()/2,l1.top+l1.height(),drawingBrush);


        canvas.drawText(shopName,width/2-shopNameDim.width()/2,l2.top+l2.height(),drawingBrush);

        canvas.drawText(address,width/2-addressDim.width()/2,sadd.top+sadd.height(),drawingBrush);

        canvas.drawText(dl,dlSl.left,dlSl.top+dlSl.height(),drawingBrush);
        canvas.drawText(sl,width/2-slNoDim.width()/2+dlNoDim.width()/2-lineTopMargin,dlSl.top+dlSl.height(),drawingBrush);

        canvas.drawText(gst,l3.left,l3.top+l3.height(),drawingBrush);

        Calendar today= new GregorianCalendar();
        date+=today.get(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
        dateDim = new Rect();
        drawingBrush.getTextBounds(date,0,date.length(),dateDim);
        canvas.drawText(date,l3.right-dateDim.width(),l3.top+l3.height(),drawingBrush);

        canvas.drawText(preName,l6.left,l6.top+l6.height(),drawingBrush);
        canvas.drawText(preBy,l7.left,l7.top+l7.height(),drawingBrush);
    }
    public static void writeText(Canvas canvas, Rect location, Paint brush, String str, int textSize) {
        float oldTextSize = brush.getTextSize();
        brush.setTextSize(textSize);
        Rect strBounds = new Rect();
        brush.getTextBounds(str, 0, str.length(), strBounds);
        canvas.drawText(str, location.left + location.width() / 2 - strBounds.width() / 2, location.top + location.height() / 2 + strBounds.height() / 2, brush);
        brush.setTextSize(oldTextSize);
    }

    public static void drawLine(Canvas canvas, Rect location, Paint brush, int strokeSize)
    {
        float oldStrokeSize = brush.getStrokeWidth();
        brush.setStrokeWidth(strokeSize);
        canvas.drawLine(location.left,location.bottom,location.right,location.bottom,brush);
        brush.setStrokeWidth(oldStrokeSize);
    }


public static void drawBill(Canvas canvas,int width,int height,int bottom)
{
    Paint drawingBrush = new Paint();
    drawingBrush.setTextSize(50);
    drawingBrush.setStyle(Paint.Style.STROKE);
    drawingBrush.setColor(Color.WHITE);
    drawingBrush.setStrokeWidth(2);
    //Maake the entire canvas white
    drawingBrush.setAntiAlias(true);
    canvas.drawPaint(drawingBrush);
    drawingBrush.setColor(Color.BLUE);
   int pageLeftMargin=20, pageTopMargin=20, pageRightMargin=20, pageBottomMargin=20;
    Rect pageBorder = new Rect(pageLeftMargin,pageTopMargin, width-pageRightMargin,height-pageBottomMargin);
    canvas.drawRect(pageBorder,drawingBrush);

    int boundsTopMargin=20;
    Rect businessNameBounds = new Rect(pageBorder.left,pageBorder.top+boundsTopMargin,pageBorder.right,pageBorder.top+boundsTopMargin+20);
    Rect addressLine1Bounds = new Rect(businessNameBounds.left,businessNameBounds.bottom+boundsTopMargin,businessNameBounds.right,businessNameBounds.bottom+boundsTopMargin+10);
    Rect addressLine2Bounds = new Rect(addressLine1Bounds.left,addressLine1Bounds.bottom+boundsTopMargin,addressLine1Bounds.right,addressLine1Bounds.bottom+boundsTopMargin+addressLine1Bounds.height());
    Rect phoneNoBounds = new Rect(addressLine2Bounds.left,addressLine2Bounds.bottom+boundsTopMargin,addressLine2Bounds.right,addressLine2Bounds.bottom+boundsTopMargin+addressLine2Bounds.height());
    Rect cashMemoBounds = new Rect(phoneNoBounds.left,phoneNoBounds.bottom+boundsTopMargin,phoneNoBounds.right,phoneNoBounds.bottom+boundsTopMargin+20);
    Rect cashMemoLineBounds = new Rect(480,cashMemoBounds.bottom+5,600,cashMemoBounds.bottom+5);

    Rect srNoBounds = new Rect(pageBorder.left,cashMemoBounds.bottom+boundsTopMargin,pageBorder.left+pageBorder.width()/4,cashMemoBounds.bottom+boundsTopMargin+20);
    Rect srNoValueBounds = new Rect(srNoBounds.right,cashMemoBounds.bottom+boundsTopMargin,srNoBounds.right+pageBorder.width()/4,cashMemoBounds.bottom+boundsTopMargin+srNoBounds.height());
    Rect dateBounds = new Rect(srNoValueBounds.right,cashMemoBounds.bottom+boundsTopMargin,srNoValueBounds.right+pageBorder.width()/4,cashMemoBounds.bottom+boundsTopMargin+srNoBounds.height());
    Rect dateValueBounds = new Rect(dateBounds.right, cashMemoBounds.bottom+boundsTopMargin,dateBounds.right+pageBorder.width()/4,cashMemoBounds.bottom+boundsTopMargin+dateBounds.height());

    Rect line1Bounds = new Rect(pageBorder.left,srNoBounds.bottom+boundsTopMargin,pageBorder.right,srNoBounds.bottom+boundsTopMargin);
    Rect line2Bounds = new Rect(pageBorder.left,line1Bounds.bottom+70,pageBorder.right,line1Bounds.bottom+70);
    Rect line3Bounds = new Rect(pageBorder.left,bottom-150,pageBorder.right,bottom-150);

    Rect quantityBounds = new Rect(pageBorder.left,line1Bounds.bottom+boundsTopMargin,pageBorder.left+pageBorder.width()/6,line1Bounds.bottom+boundsTopMargin+20);
    Rect rateBounds = new Rect(quantityBounds.right,line1Bounds.bottom+boundsTopMargin,quantityBounds.right+pageBorder.width()/6, line1Bounds.bottom+boundsTopMargin+quantityBounds.height());
    Rect descriptionBounds = new Rect(rateBounds.right,line1Bounds.bottom+boundsTopMargin,rateBounds.right+pageBorder.width()/2,line1Bounds.bottom+boundsTopMargin+quantityBounds.height());
    Rect totalBounds = new Rect(descriptionBounds.right,line1Bounds.bottom+boundsTopMargin,descriptionBounds.right+pageBorder.width()/6,line1Bounds.bottom+boundsTopMargin+quantityBounds.height());



    Rect amountInWord = new Rect(pageBorder.left,line3Bounds.bottom+boundsTopMargin,pageBorder.left+pageBorder.width()/4,line3Bounds.bottom+boundsTopMargin+20);
    Rect amountInWordValue = new Rect(amountInWord.right,line3Bounds.bottom+boundsTopMargin,amountInWord.right+pageBorder.width()/4,line3Bounds.bottom+boundsTopMargin+amountInWord.height());
    Rect gTotal = new Rect(amountInWordValue.right,line3Bounds.bottom+boundsTopMargin,amountInWordValue.right+pageBorder.width()/4,line3Bounds.bottom+boundsTopMargin+20);
    Rect gTotalValue = new Rect(gTotal.right,line3Bounds.bottom+boundsTopMargin,gTotal.right+pageBorder.width()/4,line3Bounds.bottom+boundsTopMargin+gTotal.height());
    Rect signBounds = new Rect(pageBorder.left,amountInWord.bottom+boundsTopMargin,pageBorder.left+150,amountInWord.bottom+boundsTopMargin+20);
    Rect signBoundsValue = new Rect(signBounds.right,amountInWord.bottom+boundsTopMargin,pageBorder.width(),amountInWordValue.bottom+boundsTopMargin+signBounds.height());

    writeText(canvas,businessNameBounds,drawingBrush,"NEW PARUL MEDICAL HALL",40);
    writeText(canvas,addressLine1Bounds,drawingBrush,"AMAS(GAYA)",30);
    writeText(canvas,addressLine2Bounds,drawingBrush,"ADDRESS LINE 2",30);
    writeText(canvas,phoneNoBounds,drawingBrush,"BUSINESS PHONE NO., BUSINESS FAX NO.",30);
    writeText(canvas,cashMemoBounds,drawingBrush,"CASH MEMO", 20);
    writeText(canvas,srNoBounds,drawingBrush,"S.NO.",20);
    drawLine(canvas,srNoValueBounds,drawingBrush,3);
    writeText(canvas,dateBounds,drawingBrush,"DATE:",20);

    Calendar today= new GregorianCalendar();
    String date = today.get(Calendar.DAY_OF_MONTH) + "/" + (today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.YEAR);
    //drawLine(date,dateValueBounds,drawingBrush,3);
    writeText(canvas,dateValueBounds,drawingBrush,date,30);

    drawLine(canvas,line1Bounds,drawingBrush,3);
    drawLine(canvas,line2Bounds,drawingBrush,3);
    drawLine(canvas,line3Bounds,drawingBrush,3);

    writeText(canvas,quantityBounds,drawingBrush,"QUANTITY",20);
    writeText(canvas,rateBounds,drawingBrush,"RATE",20);
    writeText(canvas,descriptionBounds,drawingBrush,"DESCRIPTION",20);
    writeText(canvas,totalBounds,drawingBrush,"TOTAL",20);


    canvas.drawLine(quantityBounds.right,line1Bounds.top,quantityBounds.right,line3Bounds.bottom,drawingBrush);
    canvas.drawLine(rateBounds.right,line1Bounds.top,rateBounds.right,line3Bounds.bottom,drawingBrush);
    canvas.drawLine(descriptionBounds.right,line1Bounds.top,descriptionBounds.right,line3Bounds.bottom,drawingBrush);

    writeText(canvas,amountInWord,drawingBrush,"AMOUNT IN WORDS", 20);
    drawLine(canvas,amountInWordValue,drawingBrush,3);
    writeText(canvas,gTotal,drawingBrush,"G. TOTAL",20);
    drawLine(canvas,gTotalValue,drawingBrush,3);
    writeText(canvas,signBounds,drawingBrush,"SIGN",20);
    drawLine(canvas,signBoundsValue,drawingBrush,3);

}

}
