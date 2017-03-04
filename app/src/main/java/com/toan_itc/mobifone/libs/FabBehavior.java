package com.toan_itc.mobifone.libs;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import java.util.List;

/**
 * Toan.IT
 * Created by vantoan on 3/4/17.
 * Email: Huynhvantoan.itc@gmail.com
 */

public class FabBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

  public FabBehavior() { }

  public FabBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
    return dependency instanceof Snackbar.SnackbarLayout;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
    float translationY = getFabTranslationYForSnackbar(parent, child);
    float percentComplete = -translationY / dependency.getHeight();
    float scaleFactor = 1 - percentComplete;

    child.setScaleX(scaleFactor);
    child.setScaleY(scaleFactor);
    return false;
  }

  private float getFabTranslationYForSnackbar(CoordinatorLayout parent,
                                              FloatingActionButton fab) {
    float minOffset = 0;
    final List<View> dependencies = parent.getDependencies(fab);
    for (int i = 0, z = dependencies.size(); i < z; i++) {
      final View view = dependencies.get(i);
      if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
        minOffset = Math.min(minOffset,
                ViewCompat.getTranslationY(view) - view.getHeight());
      }
    }

    return minOffset;
  }
}