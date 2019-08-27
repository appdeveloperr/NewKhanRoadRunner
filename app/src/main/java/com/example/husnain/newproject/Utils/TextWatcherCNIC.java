package com.example.husnain.newproject.Utils;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextWatcherCNIC implements TextWatcher
{
    private EditText editText;
    
    public TextWatcherCNIC(final EditText editText) {
        this.editText = null;
        (this.editText = editText).setInputType(2);
        this.editText.setFilters(new InputFilter[] { new InputChar(), new InputFilter.LengthFilter(15) });
    }
    
    public void afterTextChanged(final Editable editable) {
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        try {
            final String trim = this.editText.getText().toString().trim();
            final String trim2 = trim.replaceAll("-", "").trim();
            if (trim2.length() >= 13 && (trim.length() <= trim2.length() || trim.charAt(13) != '-')) {
                this.editText.setText((CharSequence)(trim2.substring(0, 5) + "-" + trim2.substring(5, 12) + "-" + trim2.substring(12)));
                this.editText.setSelection(this.editText.getText().toString().length());
                return;
            }
            if (trim2.length() >= 6 && trim.charAt(5) != '-') {
                this.editText.setText((CharSequence)(trim2.substring(0, 5) + "-" + trim2.substring(5)));
                this.editText.setSelection(this.editText.getText().toString().length());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    class InputChar implements InputFilter
    {
        public CharSequence filter(final CharSequence charSequence, final int n, final int n2, final Spanned spanned, final int n3, final int n4) {
            for (int i = n; i < n2; ++i) {
                if (!Character.isDigit(charSequence.charAt(i)) && charSequence.charAt(i) != '-') {
                    return "";
                }
            }
            return null;
        }
    }
}
