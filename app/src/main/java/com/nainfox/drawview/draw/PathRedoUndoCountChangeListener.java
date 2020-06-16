package com.nainfox.drawview.draw;

public interface PathRedoUndoCountChangeListener {
    void onUndoCountChanged(int undoCount);

    void onRedoCountChanged(int redoCount);
}
