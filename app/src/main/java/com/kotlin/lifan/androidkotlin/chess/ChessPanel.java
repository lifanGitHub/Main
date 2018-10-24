package com.kotlin.lifan.androidkotlin.chess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.kotlin.lifan.androidkotlin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiFan on 2016/8/23.
 */
public class ChessPanel extends View implements View.OnTouchListener{
    private int winNumber = ChessConfig.WIN_NUMBER;
    private boolean whiteAction = ChessConfig.whiteAction;
    private int panelWidth;
    private float lineHeight;
    private int MAX_LINE = 13;
    private Paint paint;
    private Bitmap whitePiece;
    private Bitmap blackPiece;
    private float pieceScale = 3*1.0f/4;

    private List<Point> whiteArray = new ArrayList<>();
    private List<Point> blackArray = new ArrayList<>();
    private int winGameUser = 0;

    public ChessPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(0xff87CEEB);
        initPaint();
        setOnTouchListener(this);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(0x88000000);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);

        whitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.chess_white);
        blackPiece = BitmapFactory.decodeResource(getResources(),R.drawable.chess_black);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wideSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int size = Math.min(wideSize,heightSize);

        if (widthMode== MeasureSpec.UNSPECIFIED ){
            size = heightSize;
        }else if (heightMode== MeasureSpec.UNSPECIFIED) {
            size = wideSize;
        }

        setMeasuredDimension(size,size);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        panelWidth = w;
        lineHeight = panelWidth * 1.0f / MAX_LINE;

        int pieceSize = (int) (lineHeight * pieceScale);
        whitePiece = Bitmap.createScaledBitmap(whitePiece,pieceSize,pieceSize,true);
        blackPiece = Bitmap.createScaledBitmap(blackPiece,pieceSize,pieceSize,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawPieces(canvas);
    }

    private void drawPieces(Canvas canvas) {
        for (int i=0,n=whiteArray.size(); i<n; i++){
            Point point = whiteArray.get(i);
            canvas.drawBitmap(whitePiece,
                    (point.x + (1-pieceScale)/2)*lineHeight,
                    (point.y + (1-pieceScale)/2)*lineHeight,null);
        }
        for (int i=0,n=blackArray.size(); i<n; i++){
            Point point = blackArray.get(i);
            canvas.drawBitmap(blackPiece,
                    (point.x + (1-pieceScale)/2)*lineHeight,
                    (point.y + (1-pieceScale)/2)*lineHeight,null);
        }

    }

    private void drawBoard(Canvas canvas) {
        int w = panelWidth;
        float lh = lineHeight;

        for (int i=0; i<MAX_LINE; i++){
            int startX = (int) (lh/2);
            int endX = (int) (w-lh/2);
            int y = (int)((0.5+i)*lh);
            canvas.drawLine(startX,y,endX,y,paint);
            canvas.drawLine(y,startX,y,endX,paint);
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction()== MotionEvent.ACTION_UP){
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            Point point = getValPoint(x,y);
            if (whiteArray.contains(point) || blackArray.contains(point)){
                return false;
            }
            if (whiteAction){
                whiteArray.add(point);
            }else {
                blackArray.add(point);
            }
            invalidate();
            checkWin();
            return true;
        }
        return true;
    }

    private Point getValPoint(int x, int y) {
        return new Point((int)(x/lineHeight), (int)(y/lineHeight));
    }

    public void clear(){
        whiteArray.clear();
        blackArray.clear();
        invalidate();
    }

    public void printLog(){
        for (int i=0; i<whiteArray.size(); i++){
            Log.i("hc","X:"+whiteArray.get(i).x+"  Y:"+whiteArray.get(i).y);
        }
    }

    private void checkWin(){
        List<Point> array = blackArray;
        if (whiteAction){
            array = whiteArray;
        }
        if (array.size()==0){
            return;
        }

        winY(array);
        winX(array);
        winXX(array);
        winYY(array);

        whiteAction = !whiteAction;
    }

    private void winX(List<Point> array) {
        if (winGameUser>0)
            return;

        boolean win[] = new boolean[MAX_LINE];
        int y = array.get(array.size()-1).y;
        for (int i=0; i<MAX_LINE; i++){
            if (array.contains(new Point(i,y))){
                win[i] = true;
            }else {
                win[i] = false;
            }
        }

        if(checkArray(win))
            winGame();
    }

    private void winY(List<Point> array){
        if (winGameUser>0)
            return;

        boolean win[] = new boolean[MAX_LINE];
        int x = array.get(array.size()-1).x;
        for (int i=0; i<MAX_LINE; i++){
            if (array.contains(new Point(x,i))){
                win[i] = true;
            }else {
                win[i] = false;
            }
        }
        if(checkArray(win)){
            winGame();
        }
    }

    private void winXX(List<Point> array){
        if (winGameUser>0)
            return;

        boolean win[] = new boolean[MAX_LINE*2];
        int x = array.get(array.size()-1).x;
        int y = array.get(array.size()-1).y;
        while (x>0 && y>0){
            x--;y--;
        }
        int i = 0;
        while (x < MAX_LINE && y < MAX_LINE) {
            Point point = new Point(x, y);
            win[i] = array.contains(point);
            x++;y++;i++;
        }
        if (checkArray(win)){
            winGame();
        }
    }
    private void winYY(List<Point> array){
        if (winGameUser>0)
            return;

        boolean win[] = new boolean[MAX_LINE*2];
        int x = array.get(array.size()-1).x;
        int y = array.get(array.size()-1).y;
        while (x<MAX_LINE && y>0){
            x++;y--;
        }
        int i = 0;
        while (x >0 && y < MAX_LINE) {
            Point point = new Point(x, y);
            win[i] = array.contains(point);
            x--;y++;i++;
        }
        if (checkArray(win)){
            winGame();
        }
    }

    private boolean checkArray(boolean[] win) {
        int flag=0;
        for (int j=0; j<win.length; j++){
            if (!win[j])
                continue;
            for (int i=j; i<win.length; i++) {
                if (win[i]) {
                    flag++;
                } else {
                    flag = 0;
                    break;
                }
                if (flag == winNumber) {
                    return true;
                }
            }
        }
        return false;
    }
    public void resetGame(int line ,int n){
        MAX_LINE = line + 1;
        winNumber = n;
        clear();
    }

    public void winGame(){
        Toast.makeText(getContext(),"Win", Toast.LENGTH_SHORT).show();
    }
}
