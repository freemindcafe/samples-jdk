/**
 * Cron expression is used to define the constraints. Say we don't want to run a piece of code at some time. 
 * We can define that time period in a cron expression and can then check whether the current time satisfy the cron. If it does that means
 * it is not a valid time and we need to pause the computation. We can then fetch the next invalid time from cron and can do a sleep for that
 * duration.
 */
package com.freemindcafe.quartz.sample1;