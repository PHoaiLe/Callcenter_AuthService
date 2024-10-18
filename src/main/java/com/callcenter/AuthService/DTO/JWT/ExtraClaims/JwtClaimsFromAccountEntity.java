package com.callcenter.AuthService.DTO.JWT.ExtraClaims;

import com.callcenter.AuthService.DTO.JWT.JwtExtraClaims;
import com.callcenter.AuthService.Entities.AccountEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtClaimsFromAccountEntity extends JwtExtraClaims<AccountEntity, String>
{

    public JwtClaimsFromAccountEntity(AccountEntity accountEntity)
    {
        super(accountEntity);
    }

    @Override
    public String getKey() {
        return super.getValue().getId();
    }

    @Override
    public Map<String, Object> toMap()
    {
        AccountEntity entity = super.getValue();

        String randomUUID = UUID.randomUUID().toString();

        Map<String, Object> map = new HashMap<>();
        map.put(JwtExtraClaims.ACCOUNT_ID, this.getKey());
        map.put(JwtExtraClaims.USER_ROLE, entity.getRole());
        map.put(JwtExtraClaims.REGISTER_TYPE, entity.getRegister_type());
        map.put(JwtExtraClaims.NONCE, randomUUID);

        return map;
    }
}
