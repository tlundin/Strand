package com.teraim.strand.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import android.os.Message;
import android.util.JsonWriter;

public class JSONify {

	/*

	public void writeJsonStream(OutputStream out, List messages) throws IOException {
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
		writer.setIndent("  ");
		writeMessagesArray(writer, messages);
		writer.close();

	}
	public void writeMessagesArray(JsonWriter writer, List messages) throws IOException {
		writer.beginArray();
		for (Message message : messages) {
			writeMessage(writer, message);
		}
		writer.endArray();
	}

	public void writeMessage(JsonWriter writer, Message message) throws IOException {
		writer.beginObject();
		writer.name("id").value(message.getId());
		writer.name("text").value(message.getText());
		if (message.getGeo() != null) {
			writer.name("geo");
			writeDoublesArray(writer, message.getGeo());
		} else {
			writer.name("geo").nullValue();
		}
		writer.name("user");
		writeUser(writer, message.getUser());
		writer.endObject();
	}

	public void writeUser(JsonWriter writer, User user) throws IOException {
		writer.beginObject();
		writer.name("name").value(user.getName());
		writer.name("followers_count").value(user.getFollowersCount());
		writer.endObject();
	}

	public void writeDoublesArray(JsonWriter writer, List doubles) throws IOException {
		writer.beginArray();
		for (Double value : doubles) {
			writer.value(value);
		}
		writer.endArray();
	}}
	*/
}
