package com.lyp.learn.mapper;

import com.lyp.learn.pojo.Orders;
import com.lyp.learn.pojo.OrdersCustom;
import com.lyp.learn.pojo.User;

import java.util.List;

/**
 * 订单mapper
 */
public interface OrdersMapperCustom {

	/**
	 * 查询订单关联查询用户信息
	 * 一对一
	 * 使用 resultType
	 *
	 */

	 List<OrdersCustom> findOrdersUser()throws Exception;

	/**
	 * 查询订单关联查询用户信息
	 * 一对一
	 * 使用resultMap
	 */

	 List<Orders> findOrdersUserResultMap()throws Exception;

	/**
	 * 查询订单(关联用户)及订单明细
	 * 一对多
	 * @return
	 * @throws Exception
	 */
	List<Orders>  findOrdersAndOrderDetailResultMap()throws Exception;

	/**
	 * 查询用户购买商品信息
	 * 多对多
	 * @return
	 * @throws Exception
	 */
	 List<User>  findUserAndItemsResultMap()throws Exception;

	/**
	 * 查询订单关联查询用户，用户信息是延迟加载
	 * @return
	 * @throws Exception
	 */
	List<Orders> findOrdersUserLazyLoading()throws Exception;

}
