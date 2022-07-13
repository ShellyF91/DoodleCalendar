package ajbc.doodle.calendar.notificationManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.core.JsonProcessingException;

import ajbc.doodle.calendar.Application;
import ajbc.doodle.calendar.entities.Notification;
import ajbc.doodle.calendar.entities.User;
import ajbc.doodle.calendar.entities.webpush.PushMessage;
import ajbc.doodle.calendar.services.MessagePushService;

public class NotificationDeliver implements Runnable{
	
	Notification notification;
	User user;
	MessagePushService messagePushService;
	
	public NotificationDeliver(Notification notification, User user, MessagePushService messagePushService) {
		this.notification = notification;
		this.messagePushService = messagePushService;
		this.user = user;
	}
	
	@Override
	public void run() {
		try {
			String messageToSend = notification.getTitle();
			messagePushService.sendPushMessage(user, messagePushService.encryptMessage(user, new PushMessage("message: ", messageToSend)));
		} catch (InvalidKeyException | JsonProcessingException | NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
	}
}
