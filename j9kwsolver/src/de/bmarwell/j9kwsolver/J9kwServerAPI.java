/**
 * Copyright (c) 2010-2012, Benjamin Marwell.  This file is
 * licensed under the Affero General Public License version 3 or later.  See
 * the COPYRIGHT file.
 */
package de.bmarwell.j9kwsolver;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bmarwell.j9kwsolver.request.ServerCheck;
import de.bmarwell.j9kwsolver.response.ServerStatus;
import de.bmarwell.j9kwsolver.util.HttpConnectorFactory;
import de.bmarwell.j9kwsolver.util.RequestToURI;
import de.bmarwell.j9kwsolver.util.ResponseUtils;

public class J9kwServerAPI {
	private static final Logger log = LoggerFactory.getLogger(J9kwServerAPI.class);
	private static Lock httpLock = new ReentrantLock();
	
	/**
	 * Empty hidden default constructor
	 */
	private J9kwServerAPI() {}
	
	
	
	/**
	 * Locks the instance.
	 */
	private void lock() {
		httpLock.lock();
	}
	
	/**
	 * unlocks the instance.
	 */
	private void unlock() {
		httpLock.unlock();
	}
	
	/**
	 * Gives the state of the 9kw-Server as an java object.
	 * @return ServerState Object or null, if State could not be determined.
	 */
	public ServerStatus getServerStatus() {
		ServerCheck sc = new ServerCheck();
		ServerStatus ss = new ServerStatus();
		String serverstate = null;
		Map<String, Integer> statepairs = null;

		lock();
		
		URI scuri = RequestToURI.ServerStatusToURI(sc);
		serverstate = HttpConnectorFactory.getBodyFromRequest(scuri);
		unlock();
		
		// TODO: Parse;
		if (StringUtils.isEmpty(serverstate)) {
			return null;
		}
		
		statepairs = ResponseUtils.StringResponseToIntMap(serverstate);
		log.debug("State des Servers: {}", statepairs);
		
		if (statepairs.containsKey("worker")) {
			ss.setWorker(statepairs.get("worker"));
		}
		if (statepairs.containsKey("inwork")) {
			ss.setInwork(statepairs.get("inwork"));
		}
		if (statepairs.containsKey("queue")) {
			ss.setQueue(statepairs.get("queue"));
		}
		
		return ss;
	}
	
	/**
	 * @return
	 */
	public static J9kwServerAPI getInstance() {
		return SingletonHolder.instance;
	}
	
	/**
	 * @author Benjamin Marwell
	 *
	 */
	private static class SingletonHolder {
		private static J9kwServerAPI instance = new J9kwServerAPI();
	}

}