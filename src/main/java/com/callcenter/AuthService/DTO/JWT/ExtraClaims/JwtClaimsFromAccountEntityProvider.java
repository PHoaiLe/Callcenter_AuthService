package com.callcenter.AuthService.DTO.JWT.ExtraClaims;

import com.callcenter.AuthService.DTO.JWT.JwtClaimsProvider;
import com.callcenter.AuthService.Entities.AccountEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtClaimsFromAccountEntityProvider extends JwtClaimsProvider<AccountEntity, String>
{

    public JwtClaimsFromAccountEntityProvider(AccountEntity accountEntity)
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
        map.put(JwtClaimsProvider.KEY, this.getKey());
        map.put(JwtClaimsProvider.USER_ROLE, entity.getRole());
        map.put(JwtClaimsProvider.REGISTER_TYPE, entity.getRegister_type());
        map.put(JwtClaimsProvider.NONCE, randomUUID);

        return map;
    }


}
