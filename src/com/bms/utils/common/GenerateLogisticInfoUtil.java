package com.bms.utils.common;

import java.util.ArrayList;
import java.util.List;

import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;

import net.sf.json.JSONArray;

public class GenerateLogisticInfoUtil {
	
	public List<LogisticStatusEntity> generateLogisticInfoUtil(LogisticEntity logisticEntity){
		List<LogisticStatusEntity> list = new ArrayList<>();
		String fromCountry = logisticEntity.getFromCountry();
		String fromCity = logisticEntity.getFromCity();
		String gatewayCity = logisticEntity.getGatewayCity();
		String expireTime = logisticEntity.getExpireTime();
		
		long beginTime = System.currentTimeMillis();
		long RandomBeginTime = (long) (beginTime+1000*60*60*(RandomUtil.getRandomTime(20 ,32)));
		long onTheWayTime1 = (long) (RandomBeginTime+1000*60*60*(RandomUtil.getRandomTime(10 ,14)));
		long onTheWayTime2 = (long) (onTheWayTime1+1000*60*60*(RandomUtil.getRandomTime(5 ,9)));
		long onTheWayTime3 = (long) (onTheWayTime2+1000*60*60*(RandomUtil.getRandomTime(35 ,43)));
		long onTheWayTime4 = (long) (onTheWayTime3+1000*60*60*(RandomUtil.getRandomTime(16 ,20)));
		long gatewayTime = (long) (onTheWayTime4+1000*60*60*(RandomUtil.getRandomTime(39 ,46)));
		System.out.println(DateFormatUtil.changeLongTimeToString(RandomBeginTime));
		if(fromCountry.equals("us")){
			LogisticStatusEntity prepare = new LogisticStatusEntity("Goods Are Consigned By Consignee ,800 Stewart St,Seattle,USA", DateFormatUtil.changeLongTimeToString(RandomBeginTime));
			LogisticStatusEntity onTheWay1 = new LogisticStatusEntity("Shipment Held For Consignee Pickup ,Seattle,USA", DateFormatUtil.changeLongTimeToString(onTheWayTime1));
			LogisticStatusEntity onTheWay2 = new LogisticStatusEntity("Shipment To The Station ,Seattle,USA", DateFormatUtil.changeLongTimeToString(onTheWayTime2));
			LogisticStatusEntity onTheWay3 = new LogisticStatusEntity("Arrival Processing Center ,San Francisco,USA", DateFormatUtil.changeLongTimeToString(onTheWayTime3));
			LogisticStatusEntity onTheWay4 = new LogisticStatusEntity("Ready to send to "+PinyinUtil.changeChineseToPinyin(gatewayCity)+",China", DateFormatUtil.changeLongTimeToString(onTheWayTime4));
			LogisticStatusEntity gateway = new LogisticStatusEntity("Parcel Delivery Customs,Waiting for clearance ,"+PinyinUtil.changeChineseToPinyin(gatewayCity)+",China", DateFormatUtil.changeLongTimeToString(gatewayTime));
			list.add(prepare);
			list.add(onTheWay1);
			list.add(onTheWay2);
			list.add(onTheWay3);
			list.add(onTheWay4);
			list.add(gateway);
		}else if(fromCountry.equals("ita")){
			LogisticStatusEntity prepare = new LogisticStatusEntity("Goods Are Consigned By Consignee ,Corso Matteotti 1/a, 20129 Milano MI", DateFormatUtil.changeLongTimeToString(RandomBeginTime));
			LogisticStatusEntity onTheWay1 = new LogisticStatusEntity("Shipment Held For Consignee Pickup ,Milano,Italy", DateFormatUtil.changeLongTimeToString(onTheWayTime1));
			LogisticStatusEntity onTheWay2 = new LogisticStatusEntity("Shipment To The Station ,Milano,Italy", DateFormatUtil.changeLongTimeToString(onTheWayTime2));
			LogisticStatusEntity onTheWay3 = new LogisticStatusEntity("Arrival Processing Center ,Chieti ,Italy", DateFormatUtil.changeLongTimeToString(onTheWayTime3));
			LogisticStatusEntity onTheWay4 = new LogisticStatusEntity("Ready to send to "+PinyinUtil.changeChineseToPinyin(gatewayCity)+",China", DateFormatUtil.changeLongTimeToString(onTheWayTime4));
			LogisticStatusEntity gateway = new LogisticStatusEntity("Parcel Delivery Customs,Waiting for clearance ,"+PinyinUtil.changeChineseToPinyin(gatewayCity)+",China", DateFormatUtil.changeLongTimeToString(gatewayTime));
			list.add(prepare);
			list.add(onTheWay1);
			list.add(onTheWay2);
			list.add(onTheWay3);
			list.add(onTheWay4);
			list.add(gateway);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		GenerateLogisticInfoUtil generateLogisticInfoUtil = new GenerateLogisticInfoUtil();
		LogisticEntity logisticEntity = new LogisticEntity();
		logisticEntity.setFromCountry("ita");
		logisticEntity.setFromCity("Milano");
		logisticEntity.setGatewayCity("上海");
		logisticEntity.setExpireTime("5");
		logisticEntity.setLogisticCompany("顺丰");
		List<LogisticStatusEntity> list = generateLogisticInfoUtil.generateLogisticInfoUtil(logisticEntity);
		System.out.println(JSONArray.fromObject(list));
	}
	
	
	
}
