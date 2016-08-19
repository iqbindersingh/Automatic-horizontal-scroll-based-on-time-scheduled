package com.demiscrolllist;

import com.purl.purlieu.ScrollViewExt;

public interface ScrollViewListener {

	void onScrollChanged(ScrollViewExt scrollView,
						 int x, int y, int oldx, int oldy);
}
