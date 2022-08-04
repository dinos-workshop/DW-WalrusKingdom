/** Defines all Triggers available inside the game such as Popup messages, Dialogues and invisible engine-modifiers */
public enum Trigger {

    /** Press A to read the sign next to you */
    MSG_READ_SIGN("MSG_READ_SIGN", TriggerStyle.POPUP_SINGLE),

    /** Press A to talk to the statue next to you */
    MSG_TALK_TO_STATUE("MSG_TALK_TO_STATUE", TriggerStyle.POPUP_SINGLE);

    // TODO: Add more Triggers

    final TriggerStyle style;
    final String name;
    Trigger(String name, TriggerStyle style) {
        this.name = name;
        this.style = style;
    }



    /** Defines the style for visible triggers such as shape and size of message popups */
    public enum TriggerStyle {

        /** A single line of text, no response options, player may walk away */
        POPUP_SINGLE(TriggerResponses.NONE),

        /** A single line of text, the player has to select either YES or NO */
        POPUP_SINGLE_CHOICE(TriggerResponses.YES_NO),

        /** Three lines of text, the player can only confirm via OK */
        POPUP_THREE_CONFIRM(TriggerResponses.OK),

        /** Three lines of text, the player has to select either YES or NO */
        POPUP_THREE_CHOICE(TriggerResponses.YES_NO),

        /** A single line alert message, no response options, the player may walk away */
        ALERT_SINGLE(TriggerResponses.NONE),
        // Used for warnings such as "entering will abort all unfinished quests" so the player can continue walking to turn back

        /** A single line alert message, the player can only confirm via OK */
        ALERT_SINGLE_CONFIRM(TriggerResponses.OK),
        // Used for stuff such as connection losses in Multiplayer (no reconnect)

        /** A save game Popup, no response options, it will disappear again after a moment */
        POPUP_SAVE(TriggerResponses.FADE),

        /** A fancy screen telling the player he has died, the player can only confirm via OK */
        DEATH_SCREEN(TriggerResponses.OK),

        /** No visible effect at all, only affecting the game engine (stuff like the scrolling behaviour) */
        INVISIBLE(TriggerResponses.NONE);

        final TriggerResponses responses;
        TriggerStyle(TriggerResponses responses) {
            this.responses = responses;
        }
    }


    /** Defines in which ways the player may react to a Trigger event such as Popup Messages */
    public enum TriggerResponses {

        /** No response options, player may walk away */
        NONE,

        /** Just a hint, the message will disappear in a moment */
        FADE,

        /** The player has to confirm via OK */
        OK,

        /** The player has to select either YES or NO */
        YES_NO
    }
}



