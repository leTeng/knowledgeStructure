package com.eTeng.ds.tree.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * 	多个Map(多个映射)的应用
 * @author GJ
 *
 */
public class UseOfMap {

	//练习：将一个单词字典的单词构建成一个单词和多个与它只有一个不同字母单词表。

	/**
	 * 	算法一：遍历单词字典，判断单词的，进行分类
	 * 		缺点：性能较慢。
	 * 		优点：程序编写简单。
	 */
	
	/**
	 * 
	 * @param words 单词列表
	 */
	public Map<String, List<String>> aglorithmOne(
			List<String> words) {
		Map<String, List<String>> wordsMap = new 
				TreeMap<String, List<String>>();
			String [] theWords = new String [words.size()];
			words.toArray(theWords);
			for(int i = 0; i<theWords.length; i++) {
				for(int j = i+1; j<theWords.length; j++) {
					//如果存在只有一个字母不同
					if(OnlyOneDiff(theWords[i],theWords[j])){
						update(wordsMap,theWords[i],theWords[j]);
						//两个单词是相互映射的。
						update(wordsMap,theWords[j],theWords[i]);
					}
				}
			}
		return wordsMap;
	}
	
	
	
	/**
	 *  算法二：将输入的单词进行长度的分类，然后将单词的一个字母去掉，作为该单词的索引。
	 *  	映射到一个Map中，如果去掉一个字母的单词作为索引查询到的单词列表存在两个单词。
	 *  	那么这两个单词只有一个不同字母。
	 */
	public Map<String,List<String>> algorithmTow(List<String> words){
		
		if(words == null) {
			return null;
		}
		Map<String, List<String>> wordsMap = new TreeMap<String, List<String>>();
		//进行长度分类
		for(String word : words) {
			update(wordsMap, String.valueOf(word.length()), word);
		}
		//对已分类单词进行去其中一个字母，再做映射。
		for(Map.Entry<String,List<String>> entry : wordsMap.entrySet()) {
			//当前长度分组单词
			List<String> groupWords = entry.getValue();
			//当前组的单词的长度
			int groupNum = Integer.parseInt(entry.getKey());
			//循环去掉其中一个字母，然后将剩下的字母作为key和单词映射
			for(int num = 0; num<groupNum; num++) {
				Map<String,List<String>> removeCharMap = 
						new TreeMap<String,List<String>>();
				for(String word : groupWords) {
					String removedChar = word.substring(0,num) + 
							word.substring(num+1);
					update(removeCharMap, removedChar, word);
				}
				/*
				 *  去掉第num位上的字母后，将剩下的字母和单词映射。
				 * 	如果剩下的字母映射到不同的单词(s1 != s2),
				 * 	那么这两个单词就是相同长度并只有一个字母不同。
				 */
				for(List<String> cliqueWords : removeCharMap.values()) {
					for(String s1 : cliqueWords) {
						if(s1.length() >= 2) {
							for(String s2 : cliqueWords) {
								if(s1 != s2) {
									update(wordsMap, s1, s2);
								}
							}
						}
					}
				}
			}
		}
		return wordsMap;
	}
	
	/**
	 *   蛮力测试(判断是否是只有一个字母不同)
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean OnlyOneDiff(String s1 , String s2) {
		
		if(s1.length() != s2.length()) {
			return false;
		}
	
		int diffCount = 0;
		for(int i = 0; i<s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				if(diffCount > 1) {
					return false;
				}
				++diffCount;
			}
		}
		return diffCount == 1;
	}
	
	/**
	 *  将对应的单词映射
	 * @param wordsMap
	 * @param key
	 * @param value
	 */
	private void update(Map<String, List<String>> wordsMap,
			String key , String value) {
		List<String> mapWordList = null;
		if(wordsMap != null) {
			mapWordList = wordsMap.get(key);
		}
		if(mapWordList == null) {
			mapWordList = new ArrayList<String>();
			wordsMap.put(key, mapWordList);
		}
		mapWordList.add(value);
	}
}
