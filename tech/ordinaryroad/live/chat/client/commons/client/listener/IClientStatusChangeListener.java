package tech.ordinaryroad.live.chat.client.commons.client.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import tech.ordinaryroad.live.chat.client.commons.client.enums.ClientStatusEnums;

public interface IClientStatusChangeListener extends PropertyChangeListener {
  default void propertyChange(PropertyChangeEvent evt) {
    onStatusChange(evt, (ClientStatusEnums)evt.getOldValue(), (ClientStatusEnums)evt.getNewValue());
  }
  
  void onStatusChange(PropertyChangeEvent paramPropertyChangeEvent, ClientStatusEnums paramClientStatusEnums1, ClientStatusEnums paramClientStatusEnums2);
}
