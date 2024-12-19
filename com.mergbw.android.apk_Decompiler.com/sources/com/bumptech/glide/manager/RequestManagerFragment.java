package com.bumptech.glide.manager;

import android.app.Fragment;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.Set;

@Deprecated
public class RequestManagerFragment extends Fragment {
    @Deprecated
    public RequestManager getRequestManager() {
        return null;
    }

    @Deprecated
    public void setRequestManager(RequestManager requestManager) {
    }

    @Deprecated
    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return new RequestManagerTreeNode() {
            public Set<RequestManager> getDescendants() {
                return Collections.emptySet();
            }
        };
    }
}
