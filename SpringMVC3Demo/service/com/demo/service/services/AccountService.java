package com.demo.service.services;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.dao.interfaces.IAccountDao;
import com.demo.model.models.Account;
import com.demo.model.models.Organization;
import com.demo.model.models.Role;
import com.demo.service.interfaces.IAccountService;
import com.demo.service.interfaces.IOrganizationService;
import com.demo.service.interfaces.IRoleService;
import com.demo.web.auth.AccountAuth;
import com.demo.web.auth.AuthHelper;
import com.demo.web.models.UserInfoModel;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.extension.StringHelper;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("AccountService")
public class AccountService extends EnableEntityService<Integer, Account, IAccountDao> implements IAccountService {
	
	/*@Autowired
    @Qualifier("AuthorityService")
	protected IAuthorityService authorityService;*/
	
	@Autowired
    @Qualifier("RoleService")
	protected IRoleService roleService;
	
	@Autowired
    @Qualifier("OrganizationService")
	protected IOrganizationService organizationService;

	@Autowired
	public AccountService(@Qualifier("AccountDao")IAccountDao accountDao){	
		super(accountDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Account> listPage(String name, String username, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(name!=null && !name.isEmpty()){
			countCriteria.add(Restrictions.eq("name", name)); 
    		listCriteria.add(Restrictions.eq("name", name)); 
		}
		if(username!=null && !username.isEmpty()){
			countCriteria.add(Restrictions.eq("username", username)); 
    		listCriteria.add(Restrictions.eq("username", username)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Account> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public boolean accountExist(String username){
		Criteria criteria = entityDao.getCriteria();
		criteria.add(Restrictions.eq("username", username));
    	criteria.setProjection(Projections.rowCount());
    	Integer count = Integer.parseInt(criteria.uniqueResult().toString());
    	if(count!=null && count>0)
    		return true;
    	else
    		return false;
	}
	
	@Override
	public void saveRegister(Account account) throws EntityOperateException, ValidatException, NoSuchAlgorithmException{
		account.setPassword(StringHelper.md5(account.getUsername()+account.getPassword()));
		account.setRegisterTime(Calendar.getInstance());
		super.save(account);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Account login(String username, String password) throws NoSuchAlgorithmException{
		Criteria criteria = entityDao.getCriteria();
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", StringHelper.md5(username+password)));	
		List<Account> accounts=criteria.list();
		
		Account ret=null;
		if(accounts!=null && !accounts.isEmpty())
			ret=accounts.get(0);
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Account getUserInfo(HttpServletRequest request){
		AccountAuth accAuth = AuthHelper.getSessionAccountAuth(request);
		String acc = null;
		if(accAuth!=null){
			acc = accAuth.getUsername();
		}
		Criteria criteria = entityDao.getCriteria();
		criteria.add(Restrictions.eq("username", acc));
		List<Account> accounts = criteria.list();
		Account ret = null;
		if(accounts != null && !accounts.isEmpty()){
			ret = accounts.get(0);
		}
		return ret;
	}
	
	@Override
	public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException{
		Account dbAccount=super.get(id);
		if(roleId!=null && roleId>0){
			Role dbRole=roleService.get(roleId);
			dbAccount.setRole(dbRole);
		}
		else
			dbAccount.setRole(null);
		if(organizationId!=null && organizationId>0){
			Organization dbOrganization=organizationService.get(organizationId);
			dbAccount.setOrganization(dbOrganization);
		}
		else
			dbAccount.setOrganization(null);
		super.update(dbAccount);
	}

	@Override
	public boolean midifyUserInfo(UserInfoModel userInfo) throws ValidatException, NoSuchAlgorithmException {
		Account acc = super.get(userInfo.getId());
		System.out.println(acc.toString());
		acc.setName(userInfo.getUserName());
		acc.setEmail(userInfo.getEmail());
		acc.setPassword(StringHelper.md5(userInfo.getAcc()+userInfo.getPassword()));
		try {
			super.update(acc);
		} catch (EntityOperateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
