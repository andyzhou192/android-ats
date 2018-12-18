package net.andy.android.ats.view.textview;

import android.widget.TextView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class BorderTextView extends TextView {

	@SuppressLint("DrawAllocation") 
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		// ½«±ß¿òÉèÎªºÚÉ«
		paint.setColor(android.graphics.Color.BLACK);
		// »­TextViewµÄ4¸ö±ß
		canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint); //ÏÂ±ß¿ò
		canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint); //×ó±ß¿ò
		canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
				this.getHeight() - 1, paint); //ÓÒ±ß¿ò
		canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1, paint); //ÉÏ±ß¿ò
	}

	public BorderTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
}
