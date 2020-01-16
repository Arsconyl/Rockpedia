package com.example.rockpedia.band;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchTest {
    private int matchScore(String input, String pattern)
    {
        if(input.length() == 0 || pattern.length() == 0)
            return 0;
        else
        {
            char in = input.charAt(0), pat = input.charAt(0);
            if(in == pat)
                return 4 + matchScore(input.substring(1), pattern.substring(1));
            else if(Character.toLowerCase(in) == Character.toLowerCase(pat))
                return 3 + matchScore(input.substring(1), pattern.substring(1));
            else if (in == ' ')
                return -1 + matchScore(input.substring(1), pattern);
            else if (pat == ' ')
                return -1 + matchScore(input, pattern.substring(1));
            else
            {
                in = StringUtils.stripAccents(String.valueOf(in)).toCharArray()[0];
                pat = StringUtils.stripAccents(String.valueOf(pat)).toCharArray()[0];
                if(in == pat)
                    return 2 + matchScore(input.substring(1), pattern.substring(1));
                else if (Character.toLowerCase(in) == Character.toLowerCase(pat))
                    return 1 + matchScore(input.substring(1), pattern.substring(1));
            }
        }
        return 0;
    }

    @Test
    public void testMatch()
    {
        assertEquals(0, matchScore("Mál akÁœ", "malakaoe"));
    }
}
