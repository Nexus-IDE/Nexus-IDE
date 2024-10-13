package com.silva.nexuside.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.silva.editor.NexusEditor;
import com.silva.nexuside.adapters.SymbolInputAdapter;

public class SymbolInputView extends RecyclerView {

    private final SymbolInputAdapter adapter = new SymbolInputAdapter();

    public SymbolInputView(Context context) {
        super(context);
        init(context);
    }

    public SymbolInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SymbolInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        setAdapter(adapter);
    }

    public void bindEditor(NexusEditor editor) {
        adapter.bindEditor(editor);
    }
}