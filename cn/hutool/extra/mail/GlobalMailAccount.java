package cn.hutool.extra.mail;

import cn.hutool.core.io.IORuntimeException;

public enum GlobalMailAccount {
  INSTANCE;
  
  private final MailAccount mailAccount;
  
  GlobalMailAccount() {
    this.mailAccount = createDefaultAccount();
  }
  
  public MailAccount getAccount() {
    return this.mailAccount;
  }
  
  private MailAccount createDefaultAccount() {
    for (String mailSettingPath : MailAccount.MAIL_SETTING_PATHS) {
      try {
        return new MailAccount(mailSettingPath);
      } catch (IORuntimeException iORuntimeException) {}
    } 
    return null;
  }
}
