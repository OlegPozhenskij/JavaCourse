package com.pozhenskii.task3;

import java.util.Date;

public record Comment(String author, Date creationDate, boolean moderated, String text) {


}
