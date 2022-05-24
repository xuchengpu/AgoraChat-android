package io.agora.chatdemo.chat.adapter;


import static io.agora.chatdemo.general.constant.DemoConstant.VIEW_TYPE_MESSAGE_CALL_ME;
import static io.agora.chatdemo.general.constant.DemoConstant.VIEW_TYPE_MESSAGE_CALL_OTHER;
import static io.agora.chat.callkit.base.EaseCallType.SINGLE_VIDEO_CALL;
import static io.agora.chat.callkit.base.EaseCallType.SINGLE_VOICE_CALL;

import android.text.TextUtils;
import android.view.ViewGroup;

import io.agora.chat.ChatMessage;
import io.agora.chat.uikit.chat.adapter.EaseMessageAdapter;
import io.agora.chatdemo.chat.CallViewHolder;
import io.agora.chatdemo.chat.ChatRowCall;
import io.agora.chat.callkit.base.EaseCallType;
import io.agora.chat.callkit.utils.EaseCallAction;
import io.agora.chat.callkit.utils.EaseCallMsgUtils;

public class CustomMessageAdapter extends EaseMessageAdapter {
    @Override
    public int getItemNotEmptyViewType(int position) {
        ChatMessage message = mData.get(position);
        String messageType = message.getStringAttribute(EaseCallMsgUtils.CALL_MSG_TYPE, "");
        String action = message.getStringAttribute(EaseCallMsgUtils.CALL_ACTION, "");
        int calltype = message.getIntAttribute(EaseCallMsgUtils.CALL_TYPE, SINGLE_VOICE_CALL.code);
        EaseCallType callkitType = EaseCallType.getfrom(calltype);
        EaseCallAction callAction = EaseCallAction.getfrom(action);
        if (TextUtils.equals(messageType, EaseCallMsgUtils.CALL_MSG_INFO)) {
            if(callAction == EaseCallAction.CALL_INVITE
                    &&(callkitType==SINGLE_VOICE_CALL||callkitType==SINGLE_VIDEO_CALL)) {
                return super.getItemNotEmptyViewType(position);
            }
            if(message.direct()==ChatMessage.Direct.SEND) {
                return VIEW_TYPE_MESSAGE_CALL_ME;
            }else {
                return VIEW_TYPE_MESSAGE_CALL_OTHER;
            }
        }
        return super.getItemNotEmptyViewType(position);
    }
    @Override
    public ViewHolder<ChatMessage> getViewHolder(ViewGroup parent, int viewType) {
        if(viewType== VIEW_TYPE_MESSAGE_CALL_ME||viewType==VIEW_TYPE_MESSAGE_CALL_OTHER) {
            return new CallViewHolder(new ChatRowCall(mContext,viewType == VIEW_TYPE_MESSAGE_CALL_ME),listener);
        }
        return super.getViewHolder(parent, viewType);
    }
}
