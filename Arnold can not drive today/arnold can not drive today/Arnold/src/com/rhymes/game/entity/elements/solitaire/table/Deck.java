package com.rhymes.game.entity.elements.solitaire.table;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.action.ActionTable;
import com.rhymes.game.entity.elements.action.SolitareAction;
import com.rhymes.game.entity.elements.solitaire.ConfigConstants;
import com.rhymes.game.entity.elements.solitaire.cards.Card;
import com.rhymes.game.entity.elements.solitaire.cards.CardBlack;
import com.rhymes.game.entity.elements.solitaire.cards.CardRed;
import com.rhymes.game.entity.elements.solitaire.cards.Clubs;
import com.rhymes.game.entity.elements.solitaire.cards.Dice;
import com.rhymes.game.entity.elements.solitaire.cards.Hearts;
import com.rhymes.game.entity.elements.solitaire.cards.Spade;
import com.rhymes.game.entity.elements.solitaire.menu.GameOverScreenSolitaire;
import com.rhymes.game.entity.elements.solitaire.menu.MenuPanel;
import com.rhymes.game.entity.elements.solitaire.menu.MenuPanelButton;
import com.rhymes.game.entity.elements.solitaire.menu.NewGameGameOver;
import com.rhymes.game.entity.elements.solitaire.menu.QuitGameOver;
import com.rhymes.game.entity.elements.solitaire.menu.RestartGameOver;
import com.rhymes.game.entity.elements.solitaire.menu.panel.CrossButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.gamepanel.GamePanel;
import com.rhymes.game.entity.elements.solitaire.menu.panel.gamepanel.button.NewGame;
import com.rhymes.game.entity.elements.solitaire.menu.panel.gamepanel.button.Quit;
import com.rhymes.game.entity.elements.solitaire.menu.panel.gamepanel.button.Restart;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.OptionsPanel;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.StatusPanel;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.Draw3Button;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.LeftHandedButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.SoundButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.StatusBarButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.TimesPerMoveButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.scoring.NoneScoring;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.scoring.StandardScore;
import com.rhymes.game.entity.elements.solitaire.menu.panel.optionpanel.button.scoring.VegasScoring;
import com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton.GameButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton.HintButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton.OptionsButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton.ThemeButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.panelbutton.UndoButton;
import com.rhymes.game.entity.elements.solitaire.menu.panel.themepanel.ThemesPanel;
import com.rhymes.game.entity.elements.solitaire.menu.panel.themepanel.ui.CardBackImage;
import com.rhymes.game.entity.elements.solitaire.menu.panel.themepanel.ui.TableBackImage;
import com.rhymes.game.entity.elements.solitaire.scoring.ResultStandard;
import com.rhymes.game.entity.elements.solitaire.scoring.ResultVegas;
import com.rhymes.game.entity.elements.ui.buttonlabel.Text;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.ICardMover;
import com.rhymes.game.interactions.IElementMover;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.core.stage.level.GameState;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class Deck extends StageBase {
	
	public CardSet card_set;
	public CardSet reserve_set;
	
	public DeckStack deck_stack;
	public TableStack table_stack;
	public FinalStack final_stack;
	
	public ICardMover cardMover;
	public IElementMover elementMover;
	public BitmapFont font;
	
	
	public GameStatus game_status;
	
	public Stack lastTouched = null;
	public SolitareAction currentAction = null;
	
//	public Button last_touched_button = null;

	public static float card_height_gap = 1/3f * ConfigConstants.card_height * ConfigConstants.ratio_h;
	
	float stack_height;
	
	int i, j, k;
	Card card;
	float x, y;
	
	public ResultStandard result_standard = null;
	public ResultVegas result_vegas = null;
	
	
	
	public Deck(CardSet reset_card_set){
		super();
	
		this.card_set = reset_card_set;
		deck_stack = new DeckStack();
		table_stack = new TableStack();
		final_stack = new FinalStack();
		
		this.result_standard = new ResultStandard();
		this.result_vegas = new ResultVegas();
		
		this.game_status = new GameStatus();
	}
	
	
	
	public Deck(){
		super();
		card_set = new CardSet();
		deck_stack = new DeckStack();
		table_stack = new TableStack();
		final_stack = new FinalStack();
		
		this.result_standard = new ResultStandard();
		this.result_vegas = new ResultVegas();
		
		this.game_status = new GameStatus();
	}
	
	 
	private void loadFonts() {
		font = TrueTypeFontFactory.createBitmapFont  
		(Gdx.files.internal(AssetConstants.FONT), Text.getFrontChars(),
				12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		font.setColor(0.8f, 0.8f, 0.8f, 0.8f);
	}
	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_BACK_2);
		assetPack.addTexture(AssetConstants.IMG_BACK_3);
		assetPack.addTexture(AssetConstants.IMG_BACK_4);
		assetPack.addTexture(AssetConstants.IMG_BACK_5);
		
		assetPack.addTexture(AssetConstants.CARD_BACK_1);
		assetPack.addTexture(AssetConstants.CARD_BACK_2);
		assetPack.addTexture(AssetConstants.CARD_BACK_3);
		assetPack.addTexture(AssetConstants.CARD_BACK_4);
		assetPack.addTexture(AssetConstants.CARD_BACK_5);
		assetPack.addTexture(AssetConstants.CARD_BACK_6);
		assetPack.addTexture(AssetConstants.CARD_BACK_7);
		assetPack.addTexture(AssetConstants.CARD_BACK_8);

		assetPack.addTexture(AssetConstants.STACK_B);
		assetPack.addTexture(AssetConstants.STACK_R);
		
		assetPack.addTexture(AssetConstants.CLUBS_1);
		assetPack.addTexture(AssetConstants.CLUBS_2);
		assetPack.addTexture(AssetConstants.CLUBS_3);
		assetPack.addTexture(AssetConstants.CLUBS_4);
		assetPack.addTexture(AssetConstants.CLUBS_5);
		assetPack.addTexture(AssetConstants.CLUBS_6);
		assetPack.addTexture(AssetConstants.CLUBS_7);
		assetPack.addTexture(AssetConstants.CLUBS_8);
		assetPack.addTexture(AssetConstants.CLUBS_9);
		assetPack.addTexture(AssetConstants.CLUBS_10);
		assetPack.addTexture(AssetConstants.CLUBS_11);
		assetPack.addTexture(AssetConstants.CLUBS_12);
		assetPack.addTexture(AssetConstants.CLUBS_13);
		
		assetPack.addTexture(AssetConstants.DICE_1);
		assetPack.addTexture(AssetConstants.DICE_2);
		assetPack.addTexture(AssetConstants.DICE_3);
		assetPack.addTexture(AssetConstants.DICE_4);
		assetPack.addTexture(AssetConstants.DICE_5);
		assetPack.addTexture(AssetConstants.DICE_6);
		assetPack.addTexture(AssetConstants.DICE_7);
		assetPack.addTexture(AssetConstants.DICE_8);
		assetPack.addTexture(AssetConstants.DICE_9);
		assetPack.addTexture(AssetConstants.DICE_10);
		assetPack.addTexture(AssetConstants.DICE_11);
		assetPack.addTexture(AssetConstants.DICE_12);
		assetPack.addTexture(AssetConstants.DICE_13);
		
		assetPack.addTexture(AssetConstants.HEARTS_1);
		assetPack.addTexture(AssetConstants.HEARTS_2);
		assetPack.addTexture(AssetConstants.HEARTS_3);
		assetPack.addTexture(AssetConstants.HEARTS_4);
		assetPack.addTexture(AssetConstants.HEARTS_5);
		assetPack.addTexture(AssetConstants.HEARTS_6);
		assetPack.addTexture(AssetConstants.HEARTS_7);
		assetPack.addTexture(AssetConstants.HEARTS_8);
		assetPack.addTexture(AssetConstants.HEARTS_9);
		assetPack.addTexture(AssetConstants.HEARTS_10);
		assetPack.addTexture(AssetConstants.HEARTS_11);
		assetPack.addTexture(AssetConstants.HEARTS_12);
		assetPack.addTexture(AssetConstants.HEARTS_13);
		
		assetPack.addTexture(AssetConstants.SPADE_1);
		assetPack.addTexture(AssetConstants.SPADE_2);
		assetPack.addTexture(AssetConstants.SPADE_3);
		assetPack.addTexture(AssetConstants.SPADE_4);
		assetPack.addTexture(AssetConstants.SPADE_5);
		assetPack.addTexture(AssetConstants.SPADE_6);
		assetPack.addTexture(AssetConstants.SPADE_7);
		assetPack.addTexture(AssetConstants.SPADE_8);
		assetPack.addTexture(AssetConstants.SPADE_9);
		assetPack.addTexture(AssetConstants.SPADE_10);
		assetPack.addTexture(AssetConstants.SPADE_11);
		assetPack.addTexture(AssetConstants.SPADE_12);
		assetPack.addTexture(AssetConstants.SPADE_13);
		
		assetPack.addTexture(AssetConstants.CARD_LAYER);

		
		assetPack.addTexture(AssetConstants.MENU_PANEL);
		assetPack.addTexture(AssetConstants.BUTTON_MENU);
		assetPack.addTexture(AssetConstants.SUB_MENU_PANEL);
		assetPack.addTexture(AssetConstants.BUTTON_ON);
		assetPack.addTexture(AssetConstants.BUTTON_OFF);
		
		assetPack.addTexture(AssetConstants.sound_off);
		assetPack.addTexture(AssetConstants.sound_on);
		assetPack.addTexture(AssetConstants.left_off);
		assetPack.addTexture(AssetConstants.left_on);
		assetPack.addTexture(AssetConstants.status_off);
		assetPack.addTexture(AssetConstants.status_on);
		assetPack.addTexture(AssetConstants.time_off);
		assetPack.addTexture(AssetConstants.time_on);
		assetPack.addTexture(AssetConstants.draw_3_off);
		assetPack.addTexture(AssetConstants.draw_3_on);
		assetPack.addTexture(AssetConstants.score_none_off);
		assetPack.addTexture(AssetConstants.score_none_on);
		assetPack.addTexture(AssetConstants.score_standard_off);
		assetPack.addTexture(AssetConstants.score_standard_on);
		assetPack.addTexture(AssetConstants.score_vegas_off);
		assetPack.addTexture(AssetConstants.score_vegas_on);

		
		assetPack.addTexture(AssetConstants.GAME_OVER);
		assetPack.addTexture(AssetConstants.BUTTON_CROSS);
		assetPack.addTexture(AssetConstants.NEW_GAME);
		assetPack.addTexture(AssetConstants.RESTART);
		assetPack.addTexture(AssetConstants.QUIT_BUTT);
		assetPack.addTexture(AssetConstants.OPTIONS_SOL);
		assetPack.addTexture(AssetConstants.THEME_SOL);
		assetPack.addTexture(AssetConstants.GAME_SOL);
		assetPack.addTexture(AssetConstants.HINTS_SOL);
		assetPack.addTexture(AssetConstants.UNDO_SOL);

		return assetPack;
	}

	
	public InteractionTouch interaction_touch_menu, interaction_touch_card, interaction_menu_button;
	
	@Override
	public void loadElements() {
		
		loadFonts();
		
		result_standard.reset();
		result_vegas.reset();
		avg_time = 0;
		count_steps = 0;
		count_time = 0;
		
		//for menu elements
		interaction_touch_menu = new InteractionTouch();
		
		///for card elements
		interaction_touch_card = new InteractionTouch();
		
		///for menu button
		interaction_menu_button = new InteractionTouch();
		
		cardMover = new ICardMover();
		this.interactions.add(cardMover);
		
		elementMover = new IElementMover();
		this.interactions.add(elementMover);
		
//		card_set.setCards();
		
		Helper.println("Loading elements...");
		this.gameControlInteractions.add(interaction_touch_card);
		this.gameControlInteractions.add(interaction_menu_button);
		
		bg = new Background(0, 0, Gdx.graphics.getWidth(), 
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_BACK_3);
	
		this.elements.add(bg);
		
		set_game();
	}
	
	private void set_game() {
		card_set.setCards();
		KnuthShuffle(card_set);
		init_deck(false);
		set_cards_on_deck(card_set);
		add_solitaire_elements();
		
		add_menu_elements();
		
		check_hint();
	}
	
	
	public void remove_solitaire_elements(){
		for(i = 0; i < deck_stack.get_non_visible_cards().get_card_stack().size(); i++){
			if(!deck_stack.get_non_visible_cards().get_card_stack().isEmpty())
				this.elements.removeValue(deck_stack.get_non_visible_cards().get_card_stack().get(i), true);
		}
		this.elements.removeValue(deck_stack.get_non_visible_cards(), true);
		
		for(i = 0; i < deck_stack.get_visible_cards().get_card_stack().size(); i++){
			if(!deck_stack.get_visible_cards().get_card_stack().isEmpty())
				this.elements.removeValue(deck_stack.get_visible_cards().get_card_stack().get(i), true);
		}
		this.elements.removeValue(deck_stack.get_visible_cards(), true);
		
		for(i = 0; i < FinalStack.NUMBER_OF_FINAL_STACK; i++){
			for(j = 0; j < final_stack.get_final_stack(i).get_card_stack().size(); j++){
				if(!final_stack.get_final_stack(i).get_card_stack().isEmpty())
					this.elements.removeValue(final_stack.get_final_stack(i).get_card_stack().get(j), true);
			}
			
			this.elements.removeValue(final_stack.get_final_stack(i), true);
		}
		
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			for(j = 0; j < table_stack.get_table_stack(i).get_card_stack().size(); j++){
				if(!table_stack.get_table_stack(i).get_card_stack().isEmpty())
					this.elements.removeValue(table_stack.get_table_stack(i).get_card_stack().get(j), true);
			}

			this.elements.removeValue(table_stack.get_table_stack(i), true);
		}
	}

	
	public void reset_game(boolean new_game){
		
		this.gameState = GameState.PLAYING;
		
		result_standard.reset();
		result_vegas.reset();
		avg_time = 0;
		count_steps = 0;
		count_time = 0;
		
		remove_solitaire_elements();
		
		deck_stack.get_non_visible_cards().get_card_stack().clear();
		deck_stack.get_visible_cards().get_card_stack().clear();
		
		for(i = 0; i < FinalStack.NUMBER_OF_FINAL_STACK; i++){
			final_stack.get_final_stack(i).get_card_stack().clear();
		}

		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			table_stack.get_table_stack(i).get_card_stack().clear();
			table_stack.get_table_stack(i).setHeight(ConfigConstants.card_height * ConfigConstants.ratio_h);
		}
		
		if(new_game){
			KnuthShuffle(card_set);
//			Helper.println("card set size : "+card_set.getCards().size());
		}
		
		init_deck(left_handed_button.isActive());
//		reArrange_cards();
		
		for(i = 0; i < card_set.getCards().size(); i++){
			card_set.getCards().get(i).set_visible(false);
			card_set.getCards().get(i).pX = 0f;
			card_set.getCards().get(i).pY = 0f;
		}
		set_cards_on_deck(card_set);
		
		add_solitaire_elements();
		
		change_CardBack_image(this.reserve_card_back_image);
		change_TableBack_image(this.reserve_table_back_image);
		
		check_hint();
	}
	
	public String label_vegas = "";
	public String label_standard = "";
	public String label_time = "";
	
	public void add_score_element() {
		
		x = 80 * ConfigConstants.ratio_w;
//		y = Gdx.graphics.getHeight() - (30 * ConfigConstants.ratio_h);
		y = - Gdx.graphics.getHeight();
		
//		score_vegas = new NumericText(x, y, 20 * ConfigConstants.ratio_w, 30 * ConfigConstants.ratio_h, 1);
		score_vegas = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, ""+result_vegas.getTotal_profit()+"$");
		this.topElements.add(score_vegas);
		
		x = Gdx.graphics.getWidth()/2f - (50 * ConfigConstants.ratio_w);
		
//		score_time = new NumericText(x, y, 20 * ConfigConstants.ratio_w, 30 * ConfigConstants.ratio_h, 1);
		score_time = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, ""+get_time_per_move()+"ms");
		this.topElements.add(score_time);
		
		x = Gdx.graphics.getWidth() - (150 * ConfigConstants.ratio_w);
		
//		score_standard = new NumericText(x, y, 20 * ConfigConstants.ratio_w, 30 * ConfigConstants.ratio_h, 1);
		score_standard = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, ""+result_standard.getTotal_points());
		this.topElements.add(score_standard);
	}

	
	//back ground
	public Background bg;

	//menu panel
	public MenuPanelButton menu_panel_button;
	public OptionsButton options_button;
	public ThemeButton theme_button;
	public GameButton game_button;
	public HintButton hint_button;
	public UndoButton undo_button;
	public CrossButton cross_button;
	
	public MenuPanel menu_panel;
	
	//option
	public OptionsPanel options_panel;
	public SoundButton sound_button;
	public LeftHandedButton left_handed_button;
	public StatusBarButton status_button;
	public TimesPerMoveButton time_per_move_button;
	public Draw3Button draw_3_button;
	
	public NoneScoring none_scoring_button;
	public StandardScore standard_scoring_button;
	public VegasScoring vegas_scoring_button;
	
//	public Text sound_label;
//	public Text left_label;
//	public Text status_label;
//	public Text time_label;
//	public Text draw3_label;
//	
//	public Text none_scoring_label;
//	public Text standard_scoring_label;
//	public Text vegas_scoring_label;
	
	public Text score_standard;
	public Text score_vegas;
	public Text score_time;
	
	public Text label_select_card_img;
	public Text label_select_table_img;
	
	public Text standard_points;
	public Text vegas_points;
	public Text time_points;
	
	public NewGameGameOver new_game_over;
	public RestartGameOver restart_game_over;
	public QuitGameOver quit_game_over;

	
	
	public StatusPanel status_panel;
	
	//theme
	public static final int NUM_OF_BG_IMG = 4;
	public static final int NUM_OF_CARD_IMG = 8;
	
	public ThemesPanel theme_panel;
	public CardBackImage card_bg_img[] = new CardBackImage[NUM_OF_CARD_IMG];
	public TableBackImage table_bg_img[] = new TableBackImage[NUM_OF_BG_IMG];
	
	//game
	public GamePanel game_panel;
	public NewGame new_game;
	public Restart restart;
	public Quit quit;
	public GameOverScreenSolitaire game_over_screen;
	
	
	
	public void add_menu_elements() {
		
		///menu button
		
		x = Gdx.graphics.getWidth()/2f - (Helper.getImageFromAssets(AssetConstants.BUTTON_MENU).getRegionWidth() * ConfigConstants.ratio_w)/2f;
//		y = (20f + ConfigConstants.menu_panel_height) * ConfigConstants.ratio_h;
		y = 20f * ConfigConstants.ratio_h;
		
		menu_panel_button = new MenuPanelButton(x, y,
				Helper.getImageFromAssets(AssetConstants.BUTTON_MENU).getRegionWidth() * ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.BUTTON_MENU).getRegionHeight() * ConfigConstants.ratio_h,
				1, AssetConstants.BUTTON_MENU);
		
		this.topElements.add(menu_panel_button);
//		subscribeToControllingInteraction(menu_panel_button, InteractionTouch.class);
		this.interaction_menu_button.subscribe(menu_panel_button);
		
		///panels
		
		x = 0f;
		y = - (Gdx.graphics.getHeight() - ((ConfigConstants.menu_panel_height + 25f) * ConfigConstants.ratio_h));
		
		status_panel = new StatusPanel(x, y, Gdx.graphics.getWidth(), 
				(ConfigConstants.menu_panel_height + 25f) * ConfigConstants.ratio_h,
				1, AssetConstants.MENU_PANEL);
		
		this.topElements.add(status_panel);
		
		add_score_element();

		
		x = 0f;
		y = -Gdx.graphics.getHeight();
		
		options_panel = new OptionsPanel(x, y,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				1, AssetConstants.SUB_MENU_PANEL);
		
		this.topElements.add(options_panel);
		
		theme_panel = new ThemesPanel(x, y,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				1, AssetConstants.SUB_MENU_PANEL);
		
		this.topElements.add(theme_panel);

		game_panel = new GamePanel(x, y,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				1, AssetConstants.SUB_MENU_PANEL);
		
		this.topElements.add(game_panel);
		
		
		x = 0f;
//		y = 0f;
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		y = -Gdx.graphics.getHeight();
		
		menu_panel = new MenuPanel(x, y,
				Gdx.graphics.getWidth(), ConfigConstants.menu_panel_height * ConfigConstants.ratio_h,
				1, AssetConstants.MENU_PANEL);
		
		this.topElements.add(menu_panel);
		
		
		///panel button
		
		x = Gdx.graphics.getWidth()/6f
		- (Helper.getImageFromAssets(AssetConstants.OPTIONS_SOL).getRegionWidth() * ConfigConstants.ratio_w)/2f;
//		y = 20f * ConfigConstants.ratio_h;
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		
		
		options_button = new OptionsButton(x, y,
				Helper.getImageFromAssets(AssetConstants.OPTIONS_SOL).getRegionWidth() * ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.OPTIONS_SOL).getRegionHeight()* ConfigConstants.ratio_h,
				1, AssetConstants.OPTIONS_SOL);
		
		this.topElements.add(options_button);
//		subscribeToControllingInteraction(options_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(options_button);

		x = 2f * Gdx.graphics.getWidth()/6f
		- (Helper.getImageFromAssets(AssetConstants.OPTIONS_SOL).getRegionWidth() * ConfigConstants.ratio_w)/2f;
//		y = 20f * ConfigConstants.ratio_h;
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		
		theme_button = new ThemeButton(x, y,
				Helper.getImageFromAssets(AssetConstants.THEME_SOL).getRegionWidth() * ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.THEME_SOL).getRegionHeight()* ConfigConstants.ratio_h,
				1, AssetConstants.THEME_SOL);
		
		this.topElements.add(theme_button);
//		subscribeToControllingInteraction(theme_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(theme_button);

		x = 3f * Gdx.graphics.getWidth()/6f
		- (Helper.getImageFromAssets(AssetConstants.OPTIONS_SOL).getRegionWidth() * ConfigConstants.ratio_w)/2f;
//		y = 20f * ConfigConstants.ratio_h;
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		
		game_button = new GameButton(x, y,
				Helper.getImageFromAssets(AssetConstants.GAME_SOL).getRegionWidth() * ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.GAME_SOL).getRegionHeight()* ConfigConstants.ratio_h,
				1, AssetConstants.GAME_SOL);
		
		this.topElements.add(game_button);
//		subscribeToControllingInteraction(game_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(game_button);
	
		x = 4f * Gdx.graphics.getWidth()/6f
		- (Helper.getImageFromAssets(AssetConstants.OPTIONS_SOL).getRegionWidth() * ConfigConstants.ratio_w)/2f;
//		y = 20f * ConfigConstants.ratio_h;
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		
		hint_button = new HintButton(x, y,
				Helper.getImageFromAssets(AssetConstants.HINTS_SOL).getRegionWidth()* ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.HINTS_SOL).getRegionHeight()* ConfigConstants.ratio_h,
				1, AssetConstants.HINTS_SOL);
		
		this.topElements.add(hint_button);
//		subscribeToControllingInteraction(hint_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(hint_button);
	
		x = 5f * Gdx.graphics.getWidth()/6f
		- (Helper.getImageFromAssets(AssetConstants.OPTIONS_SOL).getRegionWidth() * ConfigConstants.ratio_w)/2f;
//		y = 20f * ConfigConstants.ratio_h;
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		
		undo_button = new UndoButton(x, y,
				Helper.getImageFromAssets(AssetConstants.UNDO_SOL).getRegionWidth() * ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.UNDO_SOL).getRegionHeight()* ConfigConstants.ratio_h,
				1, AssetConstants.UNDO_SOL);
		
		this.topElements.add(undo_button);
//		subscribeToControllingInteraction(undo_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(undo_button);
	
		x = Gdx.graphics.getWidth()/6f - (ConfigConstants.menu_button_width * ConfigConstants.ratio_w)/2f;
//		y = 20f * ConfigConstants.ratio_h;
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		
		cross_button = new CrossButton(x, y,
				Helper.getImageFromAssets(AssetConstants.BUTTON_CROSS).getRegionWidth() * ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.BUTTON_CROSS).getRegionHeight() * ConfigConstants.ratio_h,
				1, AssetConstants.BUTTON_CROSS);
		
		this.topElements.add(cross_button);
//		subscribeToControllingInteraction(cross_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(cross_button);

//		x = 5 * Gdx.graphics.getWidth()/6f - (ConfigConstants.menu_button_width * ConfigConstants.ratio_w)/2f;
		x = Gdx.graphics.getWidth()/2f - (Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth()*ConfigConstants.ratio_w)/2f;
//		y = 20f * ConfigConstants.ratio_h;
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		
		sound_button = new SoundButton(x, y,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth()*ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionHeight()*ConfigConstants.ratio_h,
				1, AssetConstants.sound_on);
		
		this.topElements.add(sound_button);
//		subscribeToControllingInteraction(sound_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(sound_button);
		
		left_handed_button = new LeftHandedButton(x, y,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth()*ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionHeight()*ConfigConstants.ratio_h,
				1, AssetConstants.left_off);
		
		this.topElements.add(left_handed_button);
//		subscribeToControllingInteraction(left_handed_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(left_handed_button);
	
		status_button = new StatusBarButton(x, y,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth()*ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionHeight()*ConfigConstants.ratio_h,
				1, AssetConstants.status_off);
		
		this.topElements.add(status_button);
//		subscribeToControllingInteraction(status_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(status_button);

		time_per_move_button = new TimesPerMoveButton(x, y,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth()*ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionHeight()*ConfigConstants.ratio_h,
				1, AssetConstants.time_off);
		
		this.topElements.add(time_per_move_button);
//		subscribeToControllingInteraction(time_per_move_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(time_per_move_button);
	
		draw_3_button = new Draw3Button(x, y,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth()*ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionHeight()*ConfigConstants.ratio_h,
				1, AssetConstants.draw_3_off);
		
		this.topElements.add(draw_3_button);
//		subscribeToControllingInteraction(draw_3_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(draw_3_button);

		none_scoring_button = new NoneScoring(x, y,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth()*ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionHeight()*ConfigConstants.ratio_h,
				1, AssetConstants.score_none_off);
		
		this.topElements.add(none_scoring_button);
//		subscribeToControllingInteraction(none_scoring_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(none_scoring_button);
	
		standard_scoring_button = new StandardScore(x, y,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth()*ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionHeight()*ConfigConstants.ratio_h,
				1, AssetConstants.score_standard_off);
		
		this.topElements.add(standard_scoring_button);
//		subscribeToControllingInteraction(standard_scoring_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(standard_scoring_button);

		vegas_scoring_button = new VegasScoring(x, y,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionWidth() * ConfigConstants.ratio_w,
				Helper.getImageFromAssets(AssetConstants.sound_on).getRegionHeight()* ConfigConstants.ratio_h,
				1, AssetConstants.score_vegas_off);
		
		this.topElements.add(vegas_scoring_button);
//		subscribeToControllingInteraction(vegas_scoring_button, InteractionTouch.class);
		this.interaction_touch_menu.subscribe(vegas_scoring_button);
		
		x = Gdx.graphics.getWidth()/9f - (ConfigConstants.menu_button_width * ConfigConstants.ratio_w)/2f;
		
//		sound_label = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Sound");
//		this.topElements.add(sound_label);
//		
//		left_label = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Left Handed");
//		this.topElements.add(left_label);
//		
//		status_label = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Show Status");
//		this.topElements.add(status_label);
//		
//		time_label = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Time per Move");
//		this.topElements.add(time_label);
//		
//		draw3_label = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Draw 3");
//		this.topElements.add(draw3_label);
//		
//		standard_scoring_label = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Scoring Standard");
//		this.topElements.add(standard_scoring_label);
////		standard_scoring_label.getFont().getScaleX()
//		Helper.println("font scale : "+standard_scoring_label.getFont().getScaleX());
//		
//		none_scoring_label = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Scoring None");
//		this.topElements.add(none_scoring_label);
//		
//		vegas_scoring_label = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Scoring Vegas");
//		this.topElements.add(vegas_scoring_label);
		
		
		x = Gdx.graphics.getWidth()/2f - (Helper.getImageFromAssets(AssetConstants.RESTART).getRegionWidth()/2f)
		* ConfigConstants.ratio_w;
		y = -Gdx.graphics.getHeight();
		
		restart = new Restart(x, y, 
				Helper.getImageFromAssets(AssetConstants.RESTART).getRegionWidth() * ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.RESTART).getRegionHeight() * ConfigConstants.ratio_h,
				1, AssetConstants.RESTART);
		this.topElements.add(restart);
		this.interaction_touch_menu.subscribe(restart);
		
		new_game = new NewGame(x, y, 
				Helper.getImageFromAssets(AssetConstants.NEW_GAME).getRegionWidth() * ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.NEW_GAME).getRegionHeight() * ConfigConstants.ratio_h,
				1, AssetConstants.NEW_GAME);
		this.topElements.add(new_game);
		this.interaction_touch_menu.subscribe(new_game);
		
		quit = new Quit(x, y, 
				Helper.getImageFromAssets(AssetConstants.QUIT_BUTT).getRegionWidth() * ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.QUIT_BUTT).getRegionHeight()* ConfigConstants.ratio_h,
				1, AssetConstants.QUIT_BUTT);
		this.topElements.add(quit);
		this.interaction_touch_menu.subscribe(quit);
		
		///game over image
		
		x = 0f;
		game_over_screen = new GameOverScreenSolitaire(x, y, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 
				1, AssetConstants.GAME_OVER);
		this.topElements.add(game_over_screen);
		
		x = Gdx.graphics.getWidth() / 5f;
		standard_points = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Score in Points : "
				+result_vegas.getTotal_profit()+" $");
		this.topElements.add(standard_points);
		
		vegas_points = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Score in Profit : "
				+result_standard.getTotal_points());
		this.topElements.add(vegas_points);
		
		time_points = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "average time : "
				+get_time_per_move()+" ms");
		this.topElements.add(time_points);
		
		
		x = Gdx.graphics.getWidth()/2f - (Helper.getImageFromAssets(AssetConstants.RESTART).getRegionWidth()/2f)
		* ConfigConstants.ratio_w;
		y = -Gdx.graphics.getHeight();
	
		restart_game_over = new RestartGameOver(x, y, 
				Helper.getImageFromAssets(AssetConstants.RESTART).getRegionWidth()* ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.RESTART).getRegionHeight()* ConfigConstants.ratio_h, 
				1, AssetConstants.RESTART);
		this.topElements.add(restart_game_over);
		this.interaction_touch_menu.subscribe(restart_game_over);

		
		
		new_game_over = new NewGameGameOver(x, y,
				Helper.getImageFromAssets(AssetConstants.NEW_GAME).getRegionWidth() * ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.NEW_GAME).getRegionHeight() * ConfigConstants.ratio_h,
				1, AssetConstants.NEW_GAME);
		this.topElements.add(new_game_over);
		this.interaction_touch_menu.subscribe(new_game_over);

		
		quit_game_over = new QuitGameOver(x, y, 
				Helper.getImageFromAssets(AssetConstants.QUIT_BUTT).getRegionWidth() * ConfigConstants.ratio_w, 
				Helper.getImageFromAssets(AssetConstants.QUIT_BUTT).getRegionHeight()* ConfigConstants.ratio_h, 
				1, AssetConstants.QUIT_BUTT);
		this.topElements.add(quit_game_over);
		this.interaction_touch_menu.subscribe(quit_game_over);
		
		
		///card back image
		
		x = Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		y = - Gdx.graphics.getHeight();
		card_bg_img[0] = new CardBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.CARD_BACK_1);
		this.topElements.add(card_bg_img[0]);
		this.interaction_touch_menu.subscribe(card_bg_img[0]);

		
		x = 2 * Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		card_bg_img[1] = new CardBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.CARD_BACK_2);
		this.topElements.add(card_bg_img[1]);
		this.interaction_touch_menu.subscribe(card_bg_img[1]);

		
		x = 3 *Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		card_bg_img[2] = new CardBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.CARD_BACK_3);
		this.topElements.add(card_bg_img[2]);
		this.interaction_touch_menu.subscribe(card_bg_img[2]);

		
		x = 4 *Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		card_bg_img[3] = new CardBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.CARD_BACK_4);
		this.topElements.add(card_bg_img[3]);
		this.interaction_touch_menu.subscribe(card_bg_img[3]);

		
		x = 1 *Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		card_bg_img[4] = new CardBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.CARD_BACK_5);
		this.topElements.add(card_bg_img[4]);
		this.interaction_touch_menu.subscribe(card_bg_img[4]);

		
		x = 2 *Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		card_bg_img[5] = new CardBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.CARD_BACK_6);
		this.topElements.add(card_bg_img[5]);
		this.interaction_touch_menu.subscribe(card_bg_img[5]);

		
		x = 3 *Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		card_bg_img[6] = new CardBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.CARD_BACK_7);
		this.topElements.add(card_bg_img[6]);
		this.interaction_touch_menu.subscribe(card_bg_img[6]);

		
		x = 4 *Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		card_bg_img[7] = new CardBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.CARD_BACK_8);
		this.topElements.add(card_bg_img[7]);
		this.interaction_touch_menu.subscribe(card_bg_img[7]);

		
		//bg
		
		x = 1 * Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		table_bg_img[0] = new TableBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.IMG_BACK_2);
		this.topElements.add(table_bg_img[0]);
		this.interaction_touch_menu.subscribe(table_bg_img[0]);

		
		x = 2 * Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		table_bg_img[1] = new TableBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.IMG_BACK_3);
		this.topElements.add(table_bg_img[1]);
		this.interaction_touch_menu.subscribe(table_bg_img[1]);

		
		x = 3 * Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		table_bg_img[2] = new TableBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.IMG_BACK_4);
		this.topElements.add(table_bg_img[2]);
		this.interaction_touch_menu.subscribe(table_bg_img[2]);

		
		x = 4 * Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		table_bg_img[3] = new TableBackImage(x, y, 
				ConfigConstants.card_width * ConfigConstants.ratio_w, ConfigConstants.card_height * ConfigConstants.ratio_h, 1,
				AssetConstants.IMG_BACK_5);
		this.topElements.add(table_bg_img[3]);
		this.interaction_touch_menu.subscribe(table_bg_img[3]);

		
		
		x = 3 * Gdx.graphics.getWidth() / 5f - (ConfigConstants.card_width * ConfigConstants.ratio_w) / 2f;
		label_select_card_img = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Change Card!!!");
		this.topElements.add(label_select_card_img);
		
		label_select_table_img = new Text(x, y, ConfigConstants.font_width, ConfigConstants.font_height, font, "Change Table!!!");
		this.topElements.add(label_select_table_img);
		
	}


	
	Point startPoint = new Point();
	Point endPoint = new Point();
	
	public boolean draw_3card = false;
	
	@Override
	public void tick(long stepTime) {
		
		
		if(this.gameState == GameState.PLAYING){
			
//			score_standard.setNumber((long)result_standard.getTotal_points());
//			score_vegas.setNumber((long)result_vegas.getTotal_profit());
//			score_time.setNumber((long)get_time_per_move());
			
//			label_standard = ""+result_standard.getTotal_points();
//			label_time = ""+get_time_per_move();
//			label_vegas = ""+result_vegas.getTotal_profit();
			
			score_standard.setText(""+result_standard.getTotal_points());
			score_time.setText(""+get_time_per_move()+"ms");
			score_vegas.setText(""+result_vegas.getTotal_profit()+"$");
			
			standard_points.setText(""+result_standard.getTotal_points());
			time_points.setText(""+get_time_per_move()+"ms");
			vegas_points.setText(""+result_vegas.getTotal_profit()+"$");
			
//			Helper.println("standard : "+result_standard.getTotal_points()+" vegas : "+result_vegas.getTotal_profit()+
//					" time/move : "+get_time_per_move());
			
			check_game_over();
			
//			check_hint();
//			Helper.println("count hint : "+count_hint);
		}
		
//		else if(this.gameState == GameState.OVER){
//			game_over_menu();
//		}
		
		////menu works
		
		if(cross_button.isActive()){
			inActiveAllMenu();
		}
		
		if(menu_panel_button.isActive()){
			menuActive();
			
//			subscribe_menu_interaction();
//			unsubscribe_cards_interaction();
		}
		
		if(!menu_panel_button.isActive()){
			menuInactive();
			
//			unsubscribe_menu_interaction();
//			subscribe_cards_interaction();
		}
		
		if(options_button.isActive()){
			optionPanelActive();
		}
		
		if(!options_button.isActive()){	
			optionPanelInactive();
		}
		
		
		if(game_button.isActive()){
			gamePanelActive();
		}
		
		if(!game_button.isActive()){
			gamePanelInactive();
		}
		
		if(theme_button.isActive()){
			themePanelActive();
		}
		
		if(!theme_button.isActive()){
			themePanelInactive();
		}
		consume_image_selection();
		
		
//		///for status
//		if(status_button.isActive()){
//			if(time_per_move_button.isActive()){
//				time_per_move_button.unset_time();
//			}
//				
//			if(vegas_scoring_button.isActive()){
//				
//			}
//		}
	}
	

	public void themePanelActive() {
		menu_panel_button.setActive(false);
		
		y = - Gdx.graphics.getHeight();
		menu_panel_button.setY(y);
		
		y = 0f;
		theme_panel.setY(y);
		
		y = Gdx.graphics.getHeight() - 50f * ConfigConstants.ratio_h;
		cross_button.setY(y);
		
		
		y = card_bg_img[7].getTop() + 25 * ConfigConstants.ratio_h;
		label_select_card_img.setY(y);
		
		y = table_bg_img[0].getTop() + 25 * ConfigConstants.ratio_h;
		label_select_table_img.setY(y);
		
		
		
		y = 1 * Gdx.graphics.getHeight() / 5f - (ConfigConstants.card_height * ConfigConstants.ratio_h)/2f;
		
		for(i =0; i < NUM_OF_BG_IMG; i++){
			table_bg_img[i].setY(y);
		}
		
		y = 3 * Gdx.graphics.getHeight() / 5f - (ConfigConstants.card_height * ConfigConstants.ratio_h)/2f;
		
		for(i = 0; i < NUM_OF_CARD_IMG/2; i++){
			card_bg_img[i].setY(y);
		}
		
		
		y = 4 * Gdx.graphics.getHeight() / 5f - (ConfigConstants.card_height * ConfigConstants.ratio_h)/2f;
		
		for(i = NUM_OF_CARD_IMG/2; i < NUM_OF_CARD_IMG; i++){
			card_bg_img[i].setY(y);
		}
	}
	
	
	public void themePanelInactive() {
//		menu_panel_button.setActive(false);
		
//		y = 20f * ConfigConstants.ratio_h;
//		menu_panel_button.setY(y);
		
		y = - Gdx.graphics.getHeight();
		
		theme_panel.setY(y);
		
		label_select_card_img.setY(y);
		
		label_select_table_img.setY(y);
		
		for(i =0; i < NUM_OF_BG_IMG; i++){
			table_bg_img[i].setY(y);
		}
		
//		y = 3 * Gdx.graphics.getHeight() / 5f - (ConfigConstants.card_height * ConfigConstants.ratio_h)/2f;
		
		for(i = 0; i < NUM_OF_CARD_IMG; i++){
			card_bg_img[i].setY(y);
		}
	}
	
	public CardBackImage last_selected_card_img = null;
	public TableBackImage last_selected_table_back_img = null;
	
	public void consume_image_selection(){
		for(i = 0; i < NUM_OF_BG_IMG; i++){
			if(table_bg_img[i] != last_selected_table_back_img){
				table_bg_img[i].selected = false;
			}
		}
		
		for(i = 0; i < NUM_OF_CARD_IMG; i++){
			if(card_bg_img[i] != last_selected_card_img){
				card_bg_img[i].selected = false;
			}
		}
	}
	


	public void inActiveAllMenu() {
		cross_button.setActive(false);
//		subscribe_cards_interaction();
//		subscribe_menu_button();
		y = - Gdx.graphics.getHeight();
		cross_button.setY(y);
		
		//game over
		game_over_screen.setY(y);
//		vegas_points.setY(y);
//		time_points.setY(y);
//		standard_points.setY(y);
		
		menu_panel_button.setActive(false);
		options_button.setActive(false);
		theme_button.setActive(false);
		game_button.setActive(false);
		
		y = 20f * ConfigConstants.ratio_h;
		menu_panel_button.setY(y);
		
	}


	public void menuActive(){
		
		y = (20f + ConfigConstants.menu_panel_height) * ConfigConstants.ratio_h;
		menu_panel_button.setY(y);
			
		y = 0f;
		menu_panel.setY(y);
			
		y = 2f * ConfigConstants.ratio_h;
		options_button.setY(y);
		theme_button.setY(y);
		game_button.setY(y);
		hint_button.setY(y);
		undo_button.setY(y);
	}
	
	public void menuInactive(){
		
//		y = - (ConfigConstants.menu_panel_height * ConfigConstants.ratio_h);
		y = - Gdx.graphics.getHeight();
		undo_button.setY(y);
		hint_button.setY(y);
		game_button.setY(y);
		theme_button.setY(y);
		options_button.setY(y);
			
		menu_panel.setY(y);
	
		y = 20f * ConfigConstants.ratio_h;
		menu_panel_button.setY(y);
		
		
//		//consume hint
//		hint_button.consume_hint();
	}
	
	public void optionPanelActive(){
		
//		unsubscribe_menu_button();
		
		y = - Gdx.graphics.getHeight();
		menu_panel_button.setY(y);
		
		
		menu_panel_button.setActive(false);
			
		y  = 0f;
		options_panel.setY(y);
		
		y = Gdx.graphics.getHeight() - 50f * ConfigConstants.ratio_h;
		cross_button.setY(y);
		
		y = Gdx.graphics.getHeight() - 150f * ConfigConstants.ratio_h;
		sound_button.setY(y);
//		sound_label.setY(y);
		
		y = Gdx.graphics.getHeight() - 250f * ConfigConstants.ratio_h;
		left_handed_button.setY(y);
//		left_label.setY(y);
		
		y = Gdx.graphics.getHeight() - 350f * ConfigConstants.ratio_h;
		status_button.setY(y);
//		status_label.setY(y);
		
		y = Gdx.graphics.getHeight() - 450f * ConfigConstants.ratio_h;
		time_per_move_button.setY(y);
//		time_label.setY(y);
		
		y = Gdx.graphics.getHeight() - 550f * ConfigConstants.ratio_h;
		draw_3_button.setY(y);
//		draw3_label.setY(y);
		
		x = Gdx.graphics.getWidth()/6f  - (ConfigConstants.menu_button_width * ConfigConstants.ratio_w)/2f;
		y = Gdx.graphics.getHeight() - 650f * ConfigConstants.ratio_h;
		none_scoring_button.setY(y);
//		none_scoring_label.setY(y);
		
		y = Gdx.graphics.getHeight() - 750f * ConfigConstants.ratio_h;
		standard_scoring_button.setY(y);
//		standard_scoring_label.setY(y);
		
		y = Gdx.graphics.getHeight() - 850f * ConfigConstants.ratio_h;
		vegas_scoring_button.setY(y);
//		vegas_scoring_label.setY(y);
			
	}
	
	public void optionPanelInactive(){
		
//		subscribe_menu_button();
//		y = 20f * ConfigConstants.ratio_h;
//		menu_panel_button.setY(y);
		
		
		
		y = - Gdx.graphics.getHeight();
		options_panel.setY(y);

//		y = - Gdx.graphics.getHeight();
		sound_button.setY(y);
//		sound_label.setY(y);
		
//		y = Gdx.graphics.getHeight() - 150f * ConfigConstants.ratio_h;
		left_handed_button.setY(y);
//		left_label.setY(y);
		
//		y = Gdx.graphics.getHeight() - 200f * ConfigConstants.ratio_h;
		status_button.setY(y);
//		status_label.setY(y);
		
//		y = Gdx.graphics.getHeight() - 250f * ConfigConstants.ratio_h;
		time_per_move_button.setY(y);
//		time_label.setY(y);
		
//		y = Gdx.graphics.getHeight() - 300f * ConfigConstants.ratio_h;
		draw_3_button.setY(y);
//		draw3_label.setY(y);
		
//		x = Gdx.graphics.getWidth()/6f  - (ConfigConstants.menu_button_width * ConfigConstants.ratio_w)/2f;
//		y = Gdx.graphics.getHeight() - 350f * ConfigConstants.ratio_h;
		none_scoring_button.setY(y);
//		none_scoring_label.setY(y);
		
//		y = Gdx.graphics.getHeight() - 350f * ConfigConstants.ratio_h;
		standard_scoring_button.setY(y);
//		standard_scoring_label.setY(y);
		
//		y = Gdx.graphics.getHeight() - 400f * ConfigConstants.ratio_h;
		vegas_scoring_button.setY(y);
//		vegas_scoring_label.setY(y);
	}
	
	
	public void gamePanelActive(){
		y = - Gdx.graphics.getHeight();
		menu_panel_button.setY(y);
		
		
		menu_panel_button.setActive(false);

		y = Gdx.graphics.getHeight() - 50f * ConfigConstants.ratio_h;
		cross_button.setY(y);
		
		y = 0;
		game_panel.setY(y);
		
		y = Gdx.graphics.getHeight()/2f;
		restart.setY(y);
		
		y = y - (100 * ConfigConstants.ratio_h);
		new_game.setY(y);
		
		y = y - (100 * ConfigConstants.ratio_h);
		quit.setY(y);
	}
	
	public void gamePanelInactive(){
//		y = 20f * ConfigConstants.ratio_h;
//		menu_panel_button.setY(y);
		
		
		y = - Gdx.graphics.getHeight();
		game_panel.setY(y);
		
		y = - Gdx.graphics.getHeight();
		restart.setY(y);
		new_game.setY(y);
		quit.setY(y);
	}
	
	public void game_over_menu(){
		////work for game over screen
		Helper.println("GAME OVER");
		
//		remove_solitaire_elements();
		
		unsubscribe_cards_interaction();
		subscribe_menu_interaction();
		
		y = 0f;
		game_over_screen.setY(y);
		
		y = Gdx.graphics.getHeight()/2f + 350f * ConfigConstants.ratio_h;
		standard_points.setY(y);
		
		y = Gdx.graphics.getHeight()/2f + 250f * ConfigConstants.ratio_h;
		vegas_points.setY(y);
		
		y = Gdx.graphics.getHeight()/2f + 160f * ConfigConstants.ratio_h;
		time_points.setY(y);
		
		y = Gdx.graphics.getHeight()/2f;
		restart_game_over.setY(y);
		
		y = Gdx.graphics.getHeight()/2f - (100 * ConfigConstants.ratio_h);
		new_game_over.setY(y);
		
		y = Gdx.graphics.getHeight()/2f - (200 * ConfigConstants.ratio_h);
		quit_game_over.setY(y);
		
	}


	public void check_game_over(){

		if(is_GameOver()){
		
//			this.gameState = GameState.OVER;
			game_over_menu();
		}
	}
	
	int flag_over, count_vis;
	boolean is_over = false;
	private boolean is_GameOver() {
		
		flag_over = 0;
		count_vis = 0;
		
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			count_vis += table_stack.get_table_stack(i).get_card_stack().size();
		}
		
		if(deck_stack.get_non_visible_cards().get_card_stack().isEmpty()){
			if(deck_stack.get_visible_cards().get_card_stack().isEmpty()){
				for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
					for(j = 0; j < table_stack.get_table_stack(i).get_card_stack().size(); j++){
						
						if(!table_stack.get_table_stack(i).get_card_stack().isEmpty()){
							if(table_stack.get_table_stack(i).get_card_stack().get(j).is_visible()){
								flag_over++;
							}
						}
					}
				}
			}
		}
		
		if(flag_over != 0 && count_vis != 0 && flag_over == count_vis)
			is_over = true;
		
		else
			is_over = false;
		
		
		if(final_stack.get_final_stack(0).get_card_stack().size() == 13
				&&	final_stack.get_final_stack(1).get_card_stack().size() == 13
				&&	final_stack.get_final_stack(2).get_card_stack().size() == 13
				&&	final_stack.get_final_stack(3).get_card_stack().size() == 13){

			
			is_over = true;
			
			}
		
		
		return is_over;
	}


	/**
	 * adding all elements and interactions subscribed.
	 */
	public void add_solitaire_elements(){
		
		///final
		
		for(i = 0; i < FinalStack.NUMBER_OF_FINAL_STACK; i++){
			for(j = 0; j < final_stack.get_final_stack(i).get_card_stack().size(); j++){
				addElement(final_stack.get_final_stack(i).get_card_stack().get(j));
//				subscribeToControllingInteraction(final_stack.get_final_stack(i).get_card_stack().get(j),
//						InteractionTouch.class);
			
				this.interaction_touch_card.subscribe(final_stack.get_final_stack(i).get_card_stack().get(j));
				
			}
			addElement(final_stack.get_final_stack(i));
//			subscribeToControllingInteraction(final_stack.get_final_stack(i), InteractionTouch.class);
			this.interaction_touch_card.subscribe(final_stack.get_final_stack(i));
			
		}
		
		
		///table
		
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			for(j = 0; j < table_stack.get_table_stack(i).get_card_stack().size(); j++){
				addElement(table_stack.get_table_stack(i).get_card_stack().get(j));
//				subscribeToControllingInteraction(table_stack.get_table_stack(i).get_card_stack().get(j),
//						InteractionTouch.class);
			
				this.interaction_touch_card.subscribe(table_stack.get_table_stack(i).get_card_stack().get(j));

			}
			addElement(table_stack.get_table_stack(i));
//			subscribeToControllingInteraction(table_stack.get_table_stack(i), InteractionTouch.class);
			this.interaction_touch_card.subscribe(table_stack.get_table_stack(i));

		}
		
		
		///deck

		for(j = 0; j < deck_stack.get_non_visible_cards().get_card_stack().size(); j++){
			addElement(deck_stack.get_non_visible_cards().get_card_stack().get(j));
//			subscribeToControllingInteraction(deck_stack.get_non_visible_cards().get_card_stack().get(j),
//					InteractionTouch.class);
			
			this.interaction_touch_card.subscribe(deck_stack.get_non_visible_cards().get_card_stack().get(j));

		}
		
		addElement(deck_stack.get_non_visible_cards());
//		subscribeToControllingInteraction(deck_stack.get_non_visible_cards(), InteractionTouch.class);
		this.interaction_touch_card.subscribe(deck_stack.get_non_visible_cards());

		
		for(j = 0; j < deck_stack.get_visible_cards().get_card_stack().size(); j++){
			addElement(deck_stack.get_visible_cards().get_card_stack().get(j));
//			subscribeToControllingInteraction(deck_stack.get_visible_cards().get_card_stack().get(j),
//					InteractionTouch.class);
			this.interaction_touch_card.subscribe(deck_stack.get_visible_cards().get_card_stack().get(j));

		}

		addElement(deck_stack.get_visible_cards());
//		subscribeToControllingInteraction(deck_stack.get_visible_cards(), InteractionTouch.class);
		this.interaction_touch_card.subscribe(deck_stack.get_visible_cards());

	}
	
	/**
	 * set card stacks on position
	 */
	public void init_deck(boolean left_handed){
		
		if(!left_handed){
			draw_right_pourtrait();
		}
		
		else if(left_handed){
			draw_left_pourtrait();
		}

	}
	
	
	public void adjust_stack_height_for_left(){
		
		for(j = 0; j< TableStack.NUMBER_OF_TABLE_STACK; j++){
			stack_height = (table_stack.get_table_stack(j)./*get_card_stack().get(0).*/getY()
					+ ConfigConstants.card_height/2f * ConfigConstants.ratio_h );
				
			table_stack.get_table_stack(j).setY(ConfigConstants.menu_panel_height + 
					(10f * ConfigConstants.ratio_h));
					
			table_stack.get_table_stack(j).setHeight(stack_height);
		}
	}
	

	public void draw_right_pourtrait(){
		x = 555f * ConfigConstants.ratio_w;
		y = 770f * ConfigConstants.ratio_h;
		
		deck_stack.get_non_visible_cards().setX(x);
		deck_stack.get_non_visible_cards().setY(y);
		

		x = x - (ConfigConstants.card_width + 10f) * ConfigConstants.ratio_w;
		
		deck_stack.get_visible_cards().setX(x);
		deck_stack.get_visible_cards().setY(y);
		
		
		
		x = 50f * ConfigConstants.ratio_w;

		for(i = 0; i < FinalStack.NUMBER_OF_FINAL_STACK; i++){
			final_stack.get_final_stack(i).setX(x);
			final_stack.get_final_stack(i).setY(y);
			
			x = x + (ConfigConstants.card_width + 10f) * ConfigConstants.ratio_w;
		}
	
		x = 50f * ConfigConstants.ratio_w;
		y = 585f * ConfigConstants.ratio_h;
		
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			table_stack.get_table_stack(i).setX(x);
			table_stack.get_table_stack(i).setY(y);
			
			x = x + (ConfigConstants.card_width + 10f) * ConfigConstants.ratio_w;
		}
	}
	
	
	
	public void draw_left_pourtrait(){
		x = 85f * ConfigConstants.ratio_w;
		y = 770f * ConfigConstants.ratio_h;
		
		deck_stack.get_non_visible_cards().setX(x);
		deck_stack.get_non_visible_cards().setY(y);
		

		x = x + (ConfigConstants.card_width + 10f) * ConfigConstants.ratio_w;
		
		deck_stack.get_visible_cards().setX(x);
		deck_stack.get_visible_cards().setY(y);
		
		
		
		x = 590f * ConfigConstants.ratio_w;

		for(i = 0; i < FinalStack.NUMBER_OF_FINAL_STACK; i++){
			final_stack.get_final_stack(i).setX(x);
			final_stack.get_final_stack(i).setY(y);
			
			x = x - (ConfigConstants.card_width + 10f) * ConfigConstants.ratio_w;
		}
	
		x = 590f * ConfigConstants.ratio_w;
		y = 585f * ConfigConstants.ratio_h;
		
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			table_stack.get_table_stack(i).setX(x);
//			table_stack.get_table_stack(i).setY((ConfigConstants.menu_panel_height + 10f) * ConfigConstants.ratio_h);
			table_stack.get_table_stack(i).setY(y);
			
			x = x - (ConfigConstants.card_width + 10f) * ConfigConstants.ratio_w;
		}
	}
	
	
	/**
	 * re-arranging cards on the basis of new stack positions
	 */
	
	public void reArrange_cards(){
		for(i = 0; i < FinalStack.NUMBER_OF_FINAL_STACK; i++){
			for(j = 0; j < final_stack.get_final_stack(i).get_card_stack().size(); j++){
				final_stack.get_final_stack(i).get_card_stack().get(j).setX(final_stack.get_final_stack(i).getX());
			}
		}
			
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			for(j = 0; j < table_stack.get_table_stack(i).get_card_stack().size(); j++){
				table_stack.get_table_stack(i).get_card_stack().get(j).setX(table_stack.get_table_stack(i).getX());
			}
		}
		
		
		for(j = 0; j < deck_stack.get_non_visible_cards().get_card_stack().size(); j++){
			deck_stack.get_non_visible_cards().get_card_stack().get(j).setX(deck_stack.get_non_visible_cards().getX());
		}
		
		for(j = 0; j < deck_stack.get_visible_cards().get_card_stack().size(); j++){
			deck_stack.get_visible_cards().get_card_stack().get(j).setX(deck_stack.get_visible_cards().getX());
		}
	}
	
	
	public float tableStackTopY;
	/**
	 * set shuffled cards on table
	 * @param card_set
	 */
	public void set_cards_on_deck(CardSet card_set){
		reserve_set = new CardSet();
		reserve_set = card_set.clone();

		tableStackTopY = table_stack.get_table_stack(0).getY(); 
		for(j = 0; j < TableStack.NUMBER_OF_TABLE_STACK; j++){

			x = table_stack.get_table_stack(j).getX();
			y = table_stack.get_table_stack(j).getY() ; 

			k = 0;

			stack_height = 0;
			while(k <= j){

				table_stack.get_table_stack(j).insert_to_Card_stack(reserve_set.getCards().
						get(reserve_set.getCards().size()-1));
				table_stack.get_table_stack(j).get_top_card_of_stack().setX(x);
				table_stack.get_table_stack(j).get_top_card_of_stack().setY(y);
					
				reserve_set.getCards().remove(reserve_set.getCards().size()-1);						

				y = y - card_height_gap ;
						
				if(k == j){
					table_stack.get_table_stack(j).get_top_card_of_stack().set_visible(true);
						
					stack_height = (table_stack.get_table_stack(j).get_card_stack().get(0).getY()
							+ ConfigConstants.card_height/2f * ConfigConstants.ratio_h );
						
					table_stack.get_table_stack(j).setY(ConfigConstants.menu_panel_height + 
							(10f * ConfigConstants.ratio_h));
							
					table_stack.get_table_stack(j).setHeight(stack_height);
				}
				k++;
			}//while loop
		}
			

		deck_stack.get_non_visible_cards().insert_to_Card_stack(reserve_set.getCards());
			
		x = deck_stack.get_non_visible_cards().getX();
		y = deck_stack.get_non_visible_cards().getY();
			
		for(i = 0; i < deck_stack.get_non_visible_cards().get_card_stack().size(); i++){
			deck_stack.get_non_visible_cards().get_card_stack().get(i).setX(x);
			deck_stack.get_non_visible_cards().get_card_stack().get(i).setY(y);
				
			deck_stack.get_non_visible_cards().get_card_stack().get(i).set_visible(false);
		}
			
//		Helper.println("Card set size: " + card_set.getCards().size());
//		Helper.println("Reserve set size: " + reserve_set.getCards().size());
	}
	
	
	/**
	 * card shuffle
	 * @param card_set
	 */
	public void KnuthShuffle(CardSet card_set)	{
		
		card = null;
		
	    for(i = 0; i < card_set.getCards().size(); i++){
	    	
	        j = i + (int)(Math.random() * (card_set.getCards().size() - i));
	        
	        card = card_set.getCards().get(i);
	        card_set.getCards().set(i, card_set.getCards().get(j));
	        card_set.getCards().set(j, card);
	    }
	}


	public void clearLastTouchedStack() {
		lastTouched.touched = false;
	}
	
	public SolitareAction getCurrentAction(){
		return currentAction;
	}
	
	public void discardAction(SolitareAction action){
		if(this.currentAction != null)
			currentAction.discard();
	}
	
	public void continueAction(SolitareAction action){
		if(this.currentAction != null)
			currentAction.act();
	}
	
	public void startAction(SolitareAction action){
//		Helper.println("Deck:StartAction(): starting a new action" );
		if(this.currentAction == null)
		{
			currentAction = action;
			currentAction.initialize();
			currentAction.start();
		}  
		else
			currentAction.start();
	}
	
	Point lastTouchedCardPoint = new Point();
	public Point getLastTouchedCardLocation()
	{
		lastTouchedCardPoint.setLocation(lastTouched.getX(),
				!lastTouched.get_card_stack().isEmpty() ? lastTouched.get_top_card_of_stack().getY() -
//						Deck.card_height_gap
						lastTouched.height_gap
						:tableStackTopY);
		
		return lastTouchedCardPoint;
	}
	
	public void startCardMover(Point startPoint, Point endPoint, ArrayList<Card> cards)
	{
		if(this.currentAction instanceof ActionTable){
			((ActionTable)currentAction).setActive(false);
			
		cardMover.start(startPoint,
				endPoint,
				cards);
//			lastCard = lastTouched.get_top_card_of_stack();
		}
		
	}
	
	
	public void startElementMover(Point startPoint, Point endPoint, ElementBase element)
	{
		if(this.currentAction instanceof ActionTable){
			((ActionTable)currentAction).setActive(false);
		elementMover.start(startPoint,
				endPoint,
				element);
//			lastCard = lastTouched.get_top_card_of_stack();
		}
		
	}
	
	
	public void stopCardMover(Point point){
		if(this.currentAction instanceof ActionTable)
			((ActionTable)currentAction).setActive(true);
	}
	
	
	
	public void unsubscribe_cards_interaction(){
//		for(i = 0; i < interactions.size; i++){
//			if(interactions.get(i) == interaction_touch_card){
				this.gameControlInteractions.removeValue(interaction_touch_card, true);
				
//				Helper.println("card un_sub");
//			}
//		}
	}
	
	
	public void subscribe_cards_interaction(){
//		for(i = 0; i < interactions.size; i++){
//			if(interactions.get(i) != interaction_touch_card){
				this.gameControlInteractions.add(interaction_touch_card);
				
//				Helper.println("card sub");
//			}
//		}
	}
	
	
	public void unsubscribe_menu_interaction(){
//		for(i = 0; i < interactions.size; i++){
//			if(interactions.get(i) == interaction_touch_menu){
				this.interactions.removeValue(interaction_touch_menu, true);
				
//				Helper.println("menu un_sub");
//			}
//		}
	}
	
	public void subscribe_menu_interaction(){
//		for(i = 0; i < interactions.size; i++){
//			if(interactions.get(i) != interaction_touch_menu){
				this.interactions.add(interaction_touch_menu);
				
//				Helper.println("menu sub");
//			}
//		}
	}
	
	
	
	public void unsubscribe_menu_button_interaction(){
//		for(i = 0; i < interactions.size; i++){
//			if(interactions.get(i) == interaction_menu_button){
				this.interactions.removeValue(interaction_menu_button, true);
				
//				Helper.println("menu button un_sub");
//			}
//		}
	}
	
	public void subscribe_menu_button_interaction(){
//		for(i = 0; i < interactions.size; i++){
//			if(interactions.get(i) != interaction_menu_button){
				this.interactions.add(interaction_menu_button);
				
//				Helper.println("menu button sub");
//			}
//		}
	}

	

	float gap = 0f;
	float g = 0f;
	
	public void reArrange_deck_cards_for_draw_3_new(boolean draw_3){
		if(draw_3){
			if(!deck_stack.get_visible_cards().get_card_stack().isEmpty()){
				
				if(left_handed_button.isActive()){
					gap = 20 * ConfigConstants.ratio_w;
					
					for(i = deck_stack.get_visible_cards().get_card_stack().size() - 1; i >= 0; i--){
						
						if(i == deck_stack.get_visible_cards().get_card_stack().size() - 1){
							if(deck_stack.get_visible_cards().get_card_stack().get(i) != null)
								g = 2 * gap;
						}
						
						else if(i ==  deck_stack.get_visible_cards().get_card_stack().size() - 2){
							if(deck_stack.get_visible_cards().get_card_stack().get(i) != null)
								g = 1 * gap;
						}
						
						else if(i ==  deck_stack.get_visible_cards().get_card_stack().size() - 3){
							if(deck_stack.get_visible_cards().get_card_stack().get(i) != null)
								g = 0 * gap;
						}
						
						else{
							if(deck_stack.get_visible_cards().get_card_stack().get(i) != null)
									g = 0 * gap;
							}				
						
						deck_stack.get_visible_cards().get_card_stack().get(i).setX(deck_stack.get_visible_cards().getX() + g);
		
					}
				}
				
				else if(!left_handed_button.isActive()){
					gap = - 20 * ConfigConstants.ratio_w;
					
					for(i = deck_stack.get_visible_cards().get_card_stack().size() - 1; i >= 0; i--){
						
						if(i == deck_stack.get_visible_cards().get_card_stack().size() - 1){
							if(deck_stack.get_visible_cards().get_card_stack().get(i) != null)
								g = 0 * gap;
						}
						
						else if(i ==  deck_stack.get_visible_cards().get_card_stack().size() - 2){
							if(deck_stack.get_visible_cards().get_card_stack().get(i) != null)
								g = 1 * gap;
						}
						
						else if(i ==  deck_stack.get_visible_cards().get_card_stack().size() - 3){
							if(deck_stack.get_visible_cards().get_card_stack().get(i) != null)
								g = 2 * gap;
						}
						
						else{
							if(deck_stack.get_visible_cards().get_card_stack().get(i) != null)
									g = 0 * gap;
							}				
						
						deck_stack.get_visible_cards().get_card_stack().get(i).setX(deck_stack.get_visible_cards().getX() + g);
		
					}
				}
				
			}
		}
		
		else if(!draw_3){
			for(i = 0; i < deck_stack.get_visible_cards().get_card_stack().size(); i++){
				deck_stack.get_visible_cards().get_card_stack().get(i).setX(deck_stack.get_visible_cards().getX());
			}
		}
	}
	
	
	
	boolean is_settable;

	public boolean table_card_logic(ArrayList<Card> source_cards,
			Stack stack) {

		is_settable = false;

		if (!source_cards.isEmpty()) {
			if (stack.get_card_stack().isEmpty()) {
				if (source_cards.get(0).getCard_id() == 13) {

					is_settable = true;
				}
			}

			else {
				if (stack.get_top_card_of_stack().getCard_id() - 1 == source_cards
						.get(0).getCard_id()) {

					if (stack.get_top_card_of_stack() instanceof CardRed
							&& source_cards.get(0) instanceof CardBlack) {

						is_settable = true;

					}

					else if (stack.get_top_card_of_stack() instanceof CardBlack
							&& source_cards.get(0) instanceof CardRed) {

						is_settable = true;

					}
				}
			}
		}
		return is_settable;
	}
	
	
	int final_hint_flag = 0;
	
	
	public boolean final_card_logic(Stack source, ArrayList<Card> source_cards, Stack stack) {
		
		if (source_cards.isEmpty()) {
			is_settable = false;
			return false;
		}
		is_settable = false;

		if (!source_cards.isEmpty() && source.get_top_card_of_stack() == source_cards.get(0)) {
			if (stack.get_card_stack().isEmpty()) {
				if (source_cards.get(source_cards.size() - 1).getCard_id() == 1 && final_hint_flag == 0) {
					is_settable = true;
					
					final_hint_flag = 1;
				}
			}

			else {

				if (stack.get_top_card_of_stack() instanceof Clubs
						&& source_cards.get(source_cards.size() - 1) instanceof Clubs) {

					if (stack.get_top_card_of_stack().getCard_id() + 1 == source_cards
							.get(source_cards.size() - 1).getCard_id()) {
						is_settable = true;
					}
				}

				else if (stack.get_top_card_of_stack() instanceof Dice
						&& source_cards.get(source_cards.size() - 1) instanceof Dice) {

					if (stack.get_top_card_of_stack().getCard_id() + 1 == source_cards
							.get(source_cards.size() - 1).getCard_id()) {
						is_settable = true;
						
					}
				}

				if (stack.get_top_card_of_stack() instanceof Hearts
						&& source_cards.get(source_cards.size() - 1) instanceof Hearts) {

					if (stack.get_top_card_of_stack().getCard_id() + 1 == source_cards
							.get(source_cards.size() - 1).getCard_id()) {
						is_settable = true;
					}
				}

				if (stack.get_top_card_of_stack() instanceof Spade
						&& source_cards.get(source_cards.size() - 1) instanceof Spade) {

					if (stack.get_top_card_of_stack().getCard_id() + 1 == source_cards
							.get(source_cards.size() - 1).getCard_id()) {
						is_settable = true;
					}
				}
			}
		}
		return is_settable;
	}
	
	
	public int count_steps = 0;
	public long count_time = 0;
	public long avg_time = 0;

	public void actionEnded(long time){
		count_steps++;

		Helper.println("New action time taken: " + time);
		count_time = count_time + time;
		
		avg_time = count_time / count_steps;
		Helper.println("time/move: " + avg_time);
	}
	
	public long get_time_per_move(){
		return avg_time;
	}
	
	
	
	float top_y;
	float hg = card_height_gap;
	float total_height;
	
	public void adjust_height_gap(Stack stack){
		if(!stack.get_card_stack().isEmpty()){
			top_y = stack.get_card_stack().get(0).getY() + (ConfigConstants.card_height * ConfigConstants.ratio_h);
			
//			Helper.println("top y : "+top_y);
			
			total_height = top_y - ((ConfigConstants.menu_panel_height + 10f) * ConfigConstants.ratio_h);

//			Helper.println("total height : "+total_height);
			
			hg = (total_height - (1.5f * ConfigConstants.card_height * ConfigConstants.ratio_h)) 
			/ (stack.get_card_stack().size() - 1);
			
//			card_height_gap = hg;
			Helper.println("........hg : "+hg+" deck.hg : "+card_height_gap);

			if(card_height_gap >= hg){
				stack.height_gap = hg;
			
				y = stack.get_card_stack().get(0).getY();
				for(i = 1; i < stack.get_card_stack().size(); i++){
					stack.get_card_stack().get(i).setY(y - stack.height_gap);
					y = stack.get_card_stack().get(i).getY();
				}
			}
			
			if(stack.height_gap < hg && card_height_gap >= hg){
				stack.height_gap = hg;
				
				y = stack.get_card_stack().get(0).getY();
				for(i = 1; i < stack.get_card_stack().size(); i++){
					stack.get_card_stack().get(i).setY(y - stack.height_gap);
					y = stack.get_card_stack().get(i).getY();
				}
			}
		}
	}
	
	
	public String reserve_card_back_image 	= AssetConstants.CARD_BACK_4;
	public String reserve_table_back_image 	= AssetConstants.IMG_BACK_3;

	
	public void change_CardBack_image(String imagePath){

		for(i = 0; i < card_set.getCards().size(); i++){
			card_set.getCards().get(i).setImage(Helper.getImageFromAssets(imagePath));
		}
		
		
		for(i = 0; i < FinalStack.NUMBER_OF_FINAL_STACK; i++){
			for(j = 0; j < final_stack.get_final_stack(i).get_card_stack().size(); j++){
				if(!final_stack.get_final_stack(i).get_card_stack().isEmpty())
					final_stack.get_final_stack(i).get_card_stack().get(j).setImage(Helper.getImageFromAssets(imagePath));
			}
		}
		
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			for(j = 0; j < table_stack.get_table_stack(i).get_card_stack().size(); j++){
				if(!table_stack.get_table_stack(i).get_card_stack().isEmpty())
					table_stack.get_table_stack(i).get_card_stack().get(j).setImage(Helper.getImageFromAssets(imagePath));
			}
		}
		
		for(i = 0; i< deck_stack.get_non_visible_cards().get_card_stack().size(); i++){
			if(!deck_stack.get_non_visible_cards().get_card_stack().isEmpty())
				deck_stack.get_non_visible_cards().get_card_stack().get(i).setImage(Helper.getImageFromAssets(imagePath));
		}
		
		for(i = 0; i< deck_stack.get_visible_cards().get_card_stack().size(); i++){
			if(!deck_stack.get_visible_cards().get_card_stack().isEmpty())
				deck_stack.get_visible_cards().get_card_stack().get(i).setImage(Helper.getImageFromAssets(imagePath));
		}
	}

	public void change_TableBack_image(String imagePath) {
		bg.setImage(Helper.getImageFromAssets(imagePath));
	}
	
	
	
	public ArrayList<Card> hint_cards = new ArrayList<Card>();
	public ArrayList<Stack> hint_source = new ArrayList<Stack>();
	public ArrayList<Stack> hint_destination = new ArrayList<Stack>();
	
	int l, m;
	
	/**
	 * 
	 * check hints
	 * 
	 */
	public void check_hint(){
		
		final_hint_flag = 0;
		
		hint_source.clear();
		hint_destination.clear();
	
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			if(!table_stack.get_table_stack(i).get_card_stack().isEmpty()){
				hint_cards.clear();
//				for(j = 0; j < table_stack.get_table_stack(i).get_card_stack().size(); j++){
				for(j = table_stack.get_table_stack(i).get_card_stack().size() - 1; j >=0 ; j--){
					
					if(table_stack.get_table_stack(i).get_card_stack().get(j).is_visible()){
						
//						hint_cards.add(table_stack.get_table_stack(i).get_card_stack().get(j));
						
						///
						hint_cards.add(table_stack.get_table_stack(i).get_card_stack().get(j));
						Collections.reverse(hint_cards);
						
						
						k = 0;
						while(k < TableStack.NUMBER_OF_TABLE_STACK){
							if(k != i){
								if(table_card_logic(hint_cards, table_stack.get_table_stack(k))){
	
									hint_source.add(table_stack.get_table_stack(i));
									hint_destination.add(table_stack.get_table_stack(k));
								}
							}
							
							k++;
						}
							
						for(l = 0; l < FinalStack.NUMBER_OF_FINAL_STACK; l++){
							if(final_card_logic(table_stack.get_table_stack(i), hint_cards, final_stack.get_final_stack(l))){
								hint_source.add(table_stack.get_table_stack(i));
								hint_destination.add(final_stack.get_final_stack(l));
							}
						}
					}
				}
			}
		}
		
		
		hint_cards.clear();
		if(!deck_stack.get_visible_cards().get_card_stack().isEmpty()){
			hint_cards.add(deck_stack.get_visible_cards().get_top_card_of_stack());
//			Helper.println("top card>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+deck_stack.get_visible_cards().get_top_card_of_stack()+" id : "
//					+deck_stack.get_visible_cards().get_top_card_of_stack().getCard_id());
		}
		
		for(i = 0; i < FinalStack.NUMBER_OF_FINAL_STACK; i++){
			if(final_card_logic(deck_stack.get_visible_cards(), hint_cards, final_stack.get_final_stack(i))){
				hint_source.add(deck_stack.get_visible_cards());
				hint_destination.add(final_stack.get_final_stack(i));
			}
		}
		
		for(i = 0; i < TableStack.NUMBER_OF_TABLE_STACK; i++){
			if(table_card_logic(hint_cards, table_stack.get_table_stack(i))){
				hint_source.add(deck_stack.get_visible_cards());
				hint_destination.add(table_stack.get_table_stack(i));
			}
		}
		
//		Helper.println("hint source size : "+hint_source.size());
//		for(i = 0; i < hint_source.size(); i++){
//			Helper.println("hint source :"+i+" : "+hint_source.get(i).getStack_tag()+" id : "+hint_source.get(i).getStack_id());
//		}
//		
//		Helper.println("hint destination size : "+hint_destination.size());
//		for(i = 0; i < hint_destination.size(); i++){
//			Helper.println("hint destination :"+i+" : "+hint_destination.get(i).getStack_tag()+" id :"+hint_destination.get(i).getStack_id());
//		}
	}
	
	
	
	/**
	 * undo game status
	 */
	public void undo(){
		
		if(game_status.getSource_stack() != null && game_status.getDestination_stack() != null){

			///source
			Helper.println("AFTER MOVE : source stack.............. : "
					+game_status.getSource_stack().getStack_tag()+" id : "+game_status.getSource_stack().getStack_id()
					+"	size : "+game_status.getSource_stack().get_card_stack().size());
			
			
			for(i = 0; i < game_status.getSource_stack().get_card_stack().size(); i++){
				this.elements.removeValue(game_status.getSource_stack().get_card_stack().get(i), true);
			}
			game_status.getSource_stack().get_card_stack().clear();
	
			for(i = 0; i < game_status.getSource_card_list().size(); i++){
				game_status.getSource_stack().get_card_stack().add(game_status.getSource_card_list().get(i));
	//			game_status.getSource_stack().get_card_stack().get(i).setLocation(game_status.getSource_location_list().get(i));
	//			game_status.getSource_stack().get_card_stack().get(i).set_visible(game_status.getSource_visibility_list().get(i));
				
	//			game_status.getSource_stack().get_card_stack().get(i).setLocation(game_status.getSource_card_list().get(i).getLocation());
		
				game_status.getSource_stack().get_card_stack().get(i).setLocation(game_status.getSource_location_list().get(i));
	//			Helper.println("Source Card i: " +  i+ " -> "+ game_status.getSource_location_list().get(i));
				game_status.getSource_stack().get_card_stack().get(i).set_visible(game_status.getSource_visibility_list().get(i));
				
				this.elements.add(game_status.getSource_stack().get_card_stack().get(i));
			}
			
			Helper.println("AFTER UNDO : source stack.............. : "
					+game_status.getSource_stack().getStack_tag()+" id : "+game_status.getSource_stack().getStack_id()
					+"	size : "+game_status.getSource_stack().get_card_stack().size());
			
			for(i = 0; i < game_status.getSource_stack().get_card_stack().size(); i++){
				Helper.println(""+game_status.getSource_stack().get_card_stack().get(i).getClass()
						+" id : "+game_status.getSource_stack().get_card_stack().get(i).getCard_id());
			}
			
			
			
			///destination
			Helper.println("AFTER MOVE : destination stack................ : "
					+game_status.getDestination_stack().getStack_tag()+" id : "+game_status.getDestination_stack().getStack_id()
					+"	size : "+game_status.getDestination_stack().get_card_stack().size());
			
			
			for(i = 0; i < game_status.getDestination_stack().get_card_stack().size(); i++){
				this.elements.removeValue(game_status.getDestination_stack().get_card_stack().get(i), true);
			}
			game_status.getDestination_stack().get_card_stack().clear();
	
			
			for(i = 0; i < game_status.getDestination_card_list().size(); i++){
				game_status.getDestination_stack().get_card_stack().add(game_status.getDestination_card_list().get(i));
	//			game_status.getDestination_stack().get_card_stack().get(i).setLocation(game_status.getDestination_location_list().get(i));
	//			game_status.getDestination_stack().get_card_stack().get(i).set_visible(game_status.getDestination_visibility_list().get(i));
	
				game_status.getDestination_stack().get_card_stack().get(i).setLocation(game_status.getDestination_card_list().get(i).getLocation());
				game_status.getDestination_stack().get_card_stack().get(i).set_visible(game_status.getDestination_card_list().get(i).is_visible());
				
				this.elements.add(game_status.getDestination_stack().get_card_stack().get(i));
			}
			
			Helper.println("AFTER UNDO : destination stack................ : "
					+game_status.getDestination_stack().getStack_tag()+" id : "+game_status.getDestination_stack().getStack_id()
					+"	size : "+game_status.getDestination_stack().get_card_stack().size());
			
			for(i = 0; i < game_status.getDestination_stack().get_card_stack().size(); i++){
				Helper.println(""+game_status.getDestination_stack().get_card_stack().get(i).getClass()
						+" id : "+game_status.getDestination_stack().get_card_stack().get(i).getCard_id());
			}
		}

		game_status.setSource_stack(null);
		game_status.setDestination_stack(null);

	}
}