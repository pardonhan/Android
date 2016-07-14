package com.just.han.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.just.han.R;

/**
 * Created by Just on 2016/7/14.\
 * 有全文和收起的TextView ExpandableTextView
 */
public class CollapsedTextView extends TextView {
    //private static final String TAG = CollapsedTextView.class.getName();

    private int maxLine = 2;//收起状态下的最大行数
    private static final String ELLIPSE = "...";
    private static final String EXPANDEDTEXT = "全文";
    private static final String COLLAPSEDTEXT = "收起";
    private String expandedText = EXPANDEDTEXT;
    private String collapsedText = COLLAPSEDTEXT;
    private int allLines;
    private boolean collapsed = true;
    private String text;
    private CharSequence collapsedCs;

    ReadMoreClickAbleSpan viewMoreSpan = new ReadMoreClickAbleSpan();

    public CollapsedTextView(Context context) {
        super(context);
        init(context, null);
    }

    public CollapsedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CollapsedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CollapsedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CollapsedTextView);
            allLines = ta.getInt(R.styleable.CollapsedTextView_trimLines, 0);
            expandedText = ta.getString(R.styleable.CollapsedTextView_expandedText);
            if (TextUtils.isEmpty(expandedText)) {
                expandedText = EXPANDEDTEXT;
            }
            collapsedText = ta.getString(R.styleable.CollapsedTextView_collapsedText);
            if (TextUtils.isEmpty(collapsedText)) {
                collapsedText = COLLAPSEDTEXT;
            }
            ta.recycle();
        }
    }

    public void showText(final String text) {
        this.text = text;
        if (allLines > 0) {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ViewTreeObserver observer = getViewTreeObserver();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        observer.removeOnGlobalLayoutListener(this);
                    } else {
                        observer.removeGlobalOnLayoutListener(this);
                    }
                    TextPaint textPaint = getPaint();
                    float width = textPaint.measureText(text);

                    int showWidth = getWidth() - getPaddingRight() - getPaddingLeft();
                    int lines = (int) (width / showWidth);

                    if (width % showWidth != 0) {
                        int expect = text.length() / lines;
                        int end = 0;
                        int lastLineEnd = 0;
                        int expandedTextWidth = (int) textPaint.measureText(ELLIPSE + expandedText);
                        for (int i = 1; i <= maxLine; i++) {
                            int tempWidth = 0;
                            if (i == maxLine) {
                                tempWidth = expandedTextWidth;
                            }
                            end += expect;
                            if (end > text.length()) {
                                end = text.length();
                            }
                            if (textPaint.measureText(text, lastLineEnd, end) > showWidth - tempWidth) {
                                end--;
                                while (textPaint.measureText(text, lastLineEnd, end) > showWidth - tempWidth) {
                                    end--;
                                }
                            } else {
                                end++;
                                while (textPaint.measureText(text, lastLineEnd, end) < showWidth - tempWidth) {
                                    end--;
                                }
                                end--;
                            }
                            lastLineEnd = end;
                        }
                        SpannableStringBuilder s = new SpannableStringBuilder(text, 0, end).append(ELLIPSE).append(expandedText);
                        collapsedCs = addClickAbleSpan(s, expandedText);
                        setText(collapsedText);
                        setMovementMethod(LinkMovementMethod.getInstance());
                    } else {
                        setText(text);
                    }
                }
            });
            setText("");
        } else {
            setText(text);
        }
    }

    private CharSequence addClickAbleSpan(SpannableStringBuilder s, CharSequence trimText) {
        s.setSpan(viewMoreSpan, s.length() - trimText.length(), s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }

    private class ReadMoreClickAbleSpan extends ClickableSpan {

        @Override
        public void onClick(View view) {
            if (collapsed) {
                SpannableStringBuilder s = new SpannableStringBuilder(text).append(collapsedText);
                setText(addClickAbleSpan(s, collapsedText));
            } else {
                setText(collapsedText);
            }
            collapsed = !collapsed;
        }
    }
}
