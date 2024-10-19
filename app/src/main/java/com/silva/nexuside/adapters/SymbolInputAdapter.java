package com.silva.nexuside.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.silva.nexuside.resources.databinding.LayoutSymbolItemBinding;
import com.silva.nexuside.models.Symbol;
import com.silva.editor.NexusEditor;

public class SymbolInputAdapter extends RecyclerView.Adapter<SymbolInputAdapter.ViewHolder> {

    private final Symbol[] symbols = getDefaultSymbols();
    private NexusEditor editor = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        final LayoutSymbolItemBinding binding;

        public ViewHolder(LayoutSymbolItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutSymbolItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Symbol symbol = symbols[position];
        holder.binding.label.setText(symbol.getLabel());

        holder.binding.getRoot().setOnClickListener(v -> insertSymbol(symbol));
    }

    @Override
    public int getItemCount() {
        return symbols.length;
    }

    private void insertSymbol(Symbol symbol) {
        if (editor == null || !editor.isEditable()) {
            return;
        }

        var controller = editor.getSnippetController();
        if ("→".equals(symbol.getLabel()) && controller.isInSnippet()) {
            controller.shiftToNextTabStop();
            return;
        }

        if ("→".equals(symbol.getLabel())) {
            editor.indentOrCommitTab();
            return;
        }

        String insertText = symbol.getInsert();
        if (insertText.length() == 2) {
            editor.insertText(insertText, 1);
        } else {
            editor.commitText(insertText, false);
        }
    }

    public void bindEditor(NexusEditor editor) {
        this.editor = editor;
    }

    private Symbol[] getDefaultSymbols() {
        String[] baseSymbols = {
            "→", "{}", "}", "()", ")", ";", "\"\"", "''", "[]", "]", "<", "/", ">", ":", "=", "+", "-", "*", "%", "&", "|", "^", "!", "?"
        };
        Symbol[] symbolsArray = new Symbol[baseSymbols.length];
        for (int i = 0; i < baseSymbols.length; i++) {
            symbolsArray[i] = new Symbol(Character.toString(baseSymbols[i].charAt(0)), baseSymbols[i]);
        }
        return symbolsArray;
    }
}