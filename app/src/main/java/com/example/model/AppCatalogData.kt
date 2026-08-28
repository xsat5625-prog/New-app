package com.example.model

object AppCatalogData {

    val categories = listOf(
        CategoryIdeas(
            categoryId = "productivity",
            categoryName = "Productivity",
            iconName = "check_circle",
            initialIdeas = listOf(
                "Daily Task Planner",
                "Habit Tracker",
                "Focus Timer",
                "Daily Notes",
                "Goal Tracker",
                "Pomodoro Timer",
                "Simple Reminder App"
            ),
            moreIdeas = listOf(
                "Daily Reflection Journal",
                "Time Blocking Scheduler",
                "Meeting Minutes Organizer",
                "Quick Scratchpad",
                "Project Milestone Tracker"
            ),
            defaultFeatures = listOf(
                "Add new task with title and description",
                "Mark tasks complete / incomplete",
                "Edit and delete tasks",
                "Filter tasks by status (All, Active, Done)",
                "Set due date & time reminders",
                "Organize tasks with custom colored tags",
                "Daily progress summary percentage",
                "Search and filter by keyword",
                "Dark & Light mode support",
                "Clear completed tasks with one click"
            )
        ),
        CategoryIdeas(
            categoryId = "finance",
            categoryName = "Finance",
            iconName = "attach_money",
            initialIdeas = listOf(
                "Expense Tracker",
                "Savings Goal Tracker",
                "Budget Planner",
                "Bill Reminder",
                "Daily Spending Log"
            ),
            moreIdeas = listOf(
                "Subscription Manager & Alert",
                "Loan & Debt Payoff Calculator",
                "Emergency Fund Progress Bar",
                "Receipt & Price Tag Note",
                "Shared Group Expense Splitter"
            ),
            defaultFeatures = listOf(
                "Record income and expense entries",
                "Categorize transactions (Food, Rent, Bills, Fun)",
                "Monthly & weekly budget summary charts",
                "Filter transactions by date range & category",
                "Set savings goals with visual progress bars",
                "Upcoming bill due date reminders",
                "Edit and delete past financial records",
                "Search transactions by title or note",
                "Export spending report to CSV text format",
                "Total balance & monthly burn rate overview"
            )
        ),
        CategoryIdeas(
            categoryId = "education",
            categoryName = "Education",
            iconName = "school",
            initialIdeas = listOf(
                "Flashcard App",
                "Study Timer",
                "Vocabulary Trainer",
                "Quiz App",
                "Homework Planner",
                "Revision Tracker"
            ),
            moreIdeas = listOf(
                "Formula & Definition Cheat Sheet",
                "Language Spaced Repetition Drill",
                "Exam Countdown & Syllabus Tracker",
                "Quick Lecture Audio/Text Notes",
                "Interactive Math Flashcards"
            ),
            defaultFeatures = listOf(
                "Create custom flashcard decks and cards",
                "Flip cards to reveal answers and definitions",
                "Spaced repetition practice mode",
                "Multiple-choice quiz with instant scoring",
                "Study session stopwatch with break timer",
                "Track daily revision streaks & mastery score",
                "Categorize cards by subject & difficulty",
                "Shuffle cards for random practice",
                "Homework deadline reminders",
                "Search cards and concepts"
            )
        ),
        CategoryIdeas(
            categoryId = "fitness",
            categoryName = "Fitness",
            iconName = "fitness_center",
            initialIdeas = listOf(
                "Workout Timer",
                "Daily Exercise Tracker",
                "Water Intake Tracker",
                "Step Goal Logger",
                "Workout Planner"
            ),
            moreIdeas = listOf(
                "HIIT & Tabata Interval Timer",
                "Gym Set & Rep Repetition Logger",
                "Body Weight & Measurement Log",
                "Running Distance & Pace Log",
                "Post-Workout Stretch Guide"
            ),
            defaultFeatures = listOf(
                "Custom interval timer (Work, Rest, Rounds)",
                "Log exercises with sets, reps, and weights",
                "Quick 1-tap water glass logging (+250ml)",
                "Daily hydration goal progress bar",
                "Sound & vibration alerts on timer finish",
                "Weekly workout history log & streak",
                "Create custom workout routines & templates",
                "Rest timer with auto-start between sets",
                "Search and filter past workouts",
                "Visual charts for fitness consistency"
            )
        ),
        CategoryIdeas(
            categoryId = "health",
            categoryName = "Health & Wellness",
            iconName = "favorite",
            initialIdeas = listOf(
                "Daily Mood Tracker",
                "Sleep Schedule Log",
                "Meditation Breathing Timer",
                "Medication Reminder",
                "Symptom & Allergy Journal"
            ),
            moreIdeas = listOf(
                "Mindful Gratitude Journal",
                "Caffeine & Sugar Intake Log",
                "Daily Energy Level Checker",
                "Posture & Eye Rest Alert",
                "Blood Pressure & Pulse Record"
            ),
            defaultFeatures = listOf(
                "Log daily mood with emoji scale & notes",
                "Track sleep hours and quality rating",
                "Box breathing & relaxation visual guide",
                "Set recurring medication pill reminders",
                "Weekly wellness & mood trend charts",
                "Private local journal entries",
                "Quick-tag symptoms and triggers",
                "Daily gratitude prompts",
                "Search historical wellness logs",
                "Gentle notification reminders"
            )
        ),
        CategoryIdeas(
            categoryId = "utilities",
            categoryName = "Utilities",
            iconName = "build",
            initialIdeas = listOf(
                "Unit Converter",
                "QR Code Scanner & Generator",
                "Tip & Bill Splitter Calculator",
                "Flashlight & Compass Tool",
                "Unit Price Comparator"
            ),
            moreIdeas = listOf(
                "Sound Decibel Meter",
                "Ruler & Level Tool",
                "Random Number & Decision Picker",
                "Network Ping & Speed Utility",
                "World Time Zone Clock"
            ),
            defaultFeatures = listOf(
                "Convert length, weight, temperature & speed",
                "Generate custom QR codes from text & URLs",
                "Split restaurant bills evenly with custom tip %",
                "1-tap unit swapping and copy result to clipboard",
                "History log of recent conversions & calculations",
                "Offline instant calculations with no lag",
                "Clean numerical keyboard with large buttons",
                "Favorite units & quick presets",
                "Dark theme optimized for night utility use",
                "Clear button to reset calculations"
            )
        ),
        CategoryIdeas(
            categoryId = "lifestyle",
            categoryName = "Lifestyle",
            iconName = "spa",
            initialIdeas = listOf(
                "Recipe Keeper & Shopping List",
                "Book Reading Tracker",
                "Daily Quote & Affirmation Journal",
                "Plant Care & Watering Reminder",
                "Daily Affirmations"
            ),
            moreIdeas = listOf(
                "Coffee Brewing Timer & Ratio Log",
                "Personal Bucket List & Dreams",
                "Wardrobe Outfit Planner",
                "Garden Planting Calendar",
                "Tea Steeping Companion"
            ),
            defaultFeatures = listOf(
                "Save recipes with ingredients & step instructions",
                "Convert recipe ingredients into shopping checklist",
                "Track current reading book page & progress %",
                "Plant watering schedule with custom frequency",
                "Daily inspiring quote widget & notification",
                "Rate and review completed books & recipes",
                "Filter recipes by cuisine or meal type",
                "Photo placeholder for books and recipes",
                "Search your personal library",
                "Favorite items for quick access"
            )
        ),
        CategoryIdeas(
            categoryId = "travel",
            categoryName = "Travel",
            iconName = "flight",
            initialIdeas = listOf(
                "Packing List Planner",
                "Travel Itinerary Organizer",
                "Trip Budget & Currency Tracker",
                "Places Visited Scratch Log",
                "City Sightseeing Checklist"
            ),
            moreIdeas = listOf(
                "Flight & Hotel Booking Note",
                "Emergency Contacts Travel Card",
                "Local Phrases & Slang Pocket Guide",
                "Souvenir & Gift Shopping List",
                "Travel Journal with Photo Notes"
            ),
            defaultFeatures = listOf(
                "Organized packing checklist by category (Clothes, Tech)",
                "Day-by-day travel itinerary schedule",
                "Multi-currency travel expense logger",
                "Interactive checklist with completion counter",
                "Offline access to saved travel details",
                "Emergency contacts & embassy numbers page",
                "Search and filter packing items",
                "Duplicate packing templates for future trips",
                "Map notes and address bookmarks",
                "Trip countdown timer"
            )
        ),
        CategoryIdeas(
            categoryId = "food",
            categoryName = "Food",
            iconName = "restaurant",
            initialIdeas = listOf(
                "Weekly Meal Planner",
                "Fridge & Pantry Inventory",
                "Grocery Price Checklist",
                "Calorie & Macro Counter",
                "Coffee Brewing Timer"
            ),
            moreIdeas = listOf(
                "Expiration Date Tracker for Food",
                "Cocktail & Mocktail Recipe Mixer",
                "Restaurant Wishlist & Rating Log",
                "Baking Ingredient Ratio Scaler",
                "Leftover Meal Suggestion Log"
            ),
            defaultFeatures = listOf(
                "Plan Breakfast, Lunch, Dinner for the week",
                "Pantry item tracker with expiration date alerts",
                "Interactive grocery checklist with department sorting",
                "1-tap move items from pantry to shopping list",
                "Search recipes and meal ideas",
                "Calorie and protein daily target bars",
                "Serving size calculator & ingredient scaler",
                "Favorite meals for quick 1-tap planning",
                "Clear checked grocery items",
                "Offline food logging"
            )
        ),
        CategoryIdeas(
            categoryId = "personal_org",
            categoryName = "Personal Organization",
            iconName = "folder",
            initialIdeas = listOf(
                "Home Inventory Tracker",
                "Vehicle Maintenance Log",
                "Subscription Organizer",
                "Warranty & Receipt Keeper",
                "Closet & Wardrobe Catalog"
            ),
            moreIdeas = listOf(
                "Gift Idea & Occasion Tracker",
                "Lending & Borrowing Tracker",
                "Pet Care & Vet Visit Log",
                "Emergency Home Guide & Manuals",
                "Subscription Renewal Countdown"
            ),
            defaultFeatures = listOf(
                "Catalog items with room/location, value & notes",
                "Track car oil changes, mileage, and service dates",
                "Monthly subscription cost breakdown",
                "Warranty expiration date reminders",
                "Search inventory by room or category",
                "Track who borrowed items and due return dates",
                "Filter active vs canceled subscriptions",
                "Total estimated household item value",
                "Export inventory list to text",
                "Clean card layout with quick status indicators"
            )
        ),
        CategoryIdeas(
            categoryId = "entertainment",
            categoryName = "Entertainment",
            iconName = "sports_esports",
            initialIdeas = listOf(
                "Board Game Scorekeeper",
                "Movie & TV Show Watchlist",
                "Dice & Coin Flipper Companion",
                "Trivia Quiz Challenge",
                "Tabletop RPG Character Sheet"
            ),
            moreIdeas = listOf(
                "Video Game Backlog Manager",
                "Card Game Life Total Counter",
                "Party Truth or Dare Generator",
                "Anime & Manga Episode Tracker",
                "Music Album Rating Journal"
            ),
            defaultFeatures = listOf(
                "Multi-player score tracking with round history",
                "Custom dice rolling (D4, D6, D8, D10, D12, D20)",
                "Watchlist with 'Plan to Watch', 'Watching', 'Done'",
                "Rate movies and games 1-5 stars with reviews",
                "High score leaderboard for local games",
                "Sound effects on dice roll and round win",
                "Player avatar and color selection",
                "Search watchlist and game records",
                "Random game/movie picker from watchlist",
                "Quick round reset and tie-breaker support"
            )
        ),
        CategoryIdeas(
            categoryId = "business",
            categoryName = "Business",
            iconName = "business_center",
            initialIdeas = listOf(
                "Simple Invoice & Receipt Maker",
                "Client Appointment Tracker",
                "Business Mileage & Fuel Log",
                "Product Stock & Inventory Counter",
                "Simple POS Cash Register"
            ),
            moreIdeas = listOf(
                "Freelance Time & Rate Tracker",
                "Sales Lead & Contact Card Log",
                "Daily Cash Register Reconciliation",
                "Service Price List & Quote Maker",
                "Vendor & Supplier Contact Directory"
            ),
            defaultFeatures = listOf(
                "Generate clean professional invoice previews",
                "Client contact list with service history",
                "Track business travel mileage with rate calculator",
                "Product catalog with stock levels and low-stock alerts",
                "Quick point-of-sale total calculation with sales tax",
                "Record cash, card, and digital payment methods",
                "Daily sales summary & revenue overview",
                "Filter appointments by date & client",
                "Search products and clients",
                "Export receipt text summary"
            )
        ),
        CategoryIdeas(
            categoryId = "student_tools",
            categoryName = "Student Tools",
            iconName = "menu_book",
            initialIdeas = listOf(
                "GPA & Grade Calculator",
                "Class Timetable Schedule",
                "Assignment Due Date Tracker",
                "Exam Countdown Timer",
                "Flashcard Study Hub"
            ),
            moreIdeas = listOf(
                "Course Syllabus & Credit Tracker",
                "Study Group Task Board",
                "Quick Formula Reference Sheet",
                "Attendance & Absence Log",
                "Textbook & Resource Checklist"
            ),
            defaultFeatures = listOf(
                "Semester GPA calculator with weighted credits",
                "Weekly class schedule grid (Monday - Friday)",
                "Assignment priority list with countdown days",
                "Upcoming test & quiz alerts",
                "Subject color coding for timetable and tasks",
                "Target grade calculator for final exams",
                "Attendance counter with minimum requirement alerts",
                "Quick notes per course and professor",
                "Filter assignments by subject and completed state",
                "Daily schedule widget overview"
            )
        ),
        CategoryIdeas(
            categoryId = "beginner_apps",
            categoryName = "Simple Beginner Apps",
            iconName = "star",
            initialIdeas = listOf(
                "Tally Counter & Clicker",
                "Simple Quick Notepad",
                "Dice Roller & Coin Flipper",
                "Tip Splitter Calculator",
                "Random Decision Maker",
                "Birthday Countdown App"
            ),
            moreIdeas = listOf(
                "Age in Days & Days Between Dates Calculator",
                "Color Palette Picker & Hex Viewer",
                "Speed Reading Text Reader",
                "Simple Password Generator",
                "Affirmation of the Day Card"
            ),
            defaultFeatures = listOf(
                "Big easy-to-tap counter buttons (+ and -)",
                "Multiple named counters with custom colors",
                "Instant auto-save on every keystroke",
                "Clean minimal layout with zero learning curve",
                "Vibration haptic feedback on button clicks",
                "Sound toggle for tap feedback",
                "Reset with confirmation dialog",
                "History log of counts and notes",
                "Search and sort entries",
                "Dark and Light theme toggle"
            )
        )
    )

    fun getCategory(idOrName: String): CategoryIdeas {
        return categories.firstOrNull { 
            it.categoryId.equals(idOrName, ignoreCase = true) || 
            it.categoryName.equals(idOrName, ignoreCase = true) 
        } ?: categories.first()
    }

    fun generateNameSuggestions(idea: String, categoryName: String): List<String> {
        val cleanIdea = idea.trim()
            .replace(Regex("(?i)\\b(an|a|the|app|simple|my|daily|for|to)\\b"), "")
            .replace(Regex("[^a-zA-Z0-9 ]"), " ")
            .split(" ")
            .filter { it.isNotBlank() }
            .joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }
            .trim()
        
        val base = if (cleanIdea.isNotBlank()) cleanIdea else "App"

        return listOf(
            "Daily $base",
            "Smart $base",
            "My $base",
            "$base Companion",
            "Simple $base Manager",
            "$base Pro",
            "Quick $base",
            "$base Hub",
            "$base Tracker",
            "Pocket $base"
        ).distinct()
    }

    fun getSuggestedFeaturesForIdea(idea: String, categoryName: String): List<String> {
        val lower = idea.lowercase()
        val cat = getCategory(categoryName)
        
        val specificFeatures = when {
            lower.contains("expense") || lower.contains("budget") || lower.contains("spend") || lower.contains("money") -> listOf(
                "Add expense with amount, category & date",
                "Edit and delete expense entries",
                "Expense categories (Food, Transport, Bills, Shopping)",
                "Date selection with quick date pickers",
                "Monthly & weekly spending totals",
                "Visual spending summary bar/pie breakdown",
                "Local transaction history log",
                "Search entries by title or note",
                "Set monthly budget limit with warning indicator",
                "Clear all data with safety confirmation",
                "Export spending history as text",
                "Filter transactions by category or month"
            )
            lower.contains("timer") || lower.contains("pomodoro") || lower.contains("focus") || lower.contains("stopwatch") -> listOf(
                "Start countdown timer with smooth visual progress ring",
                "Pause and resume timer anytime",
                "Reset timer to initial duration",
                "Custom duration picker (Minutes and Seconds)",
                "Preset buttons (5m, 15m, 25m, 45m, 60m)",
                "Audio beep and vibration alert on completion",
                "Session history log with timestamp & duration",
                "Focus streak counter for completed sessions",
                "Optional break interval countdown",
                "Keep screen on while timer is running",
                "Clean distraction-free full screen mode",
                "Dark mode optimized for battery conservation"
            )
            lower.contains("habit") || lower.contains("routine") -> listOf(
                "Create new habit with custom name, icon & color",
                "1-tap daily check-in completion toggle",
                "Current and longest daily streak counter",
                "Weekly and monthly completion calendar grid",
                "Custom frequency (Every day, Weekdays, Custom days)",
                "Daily reminder notification at chosen time",
                "Archive or delete completed habits",
                "Motivational progress percentage for the day",
                "Habit category tags (Health, Work, Learning)",
                "Reorder habits by priority or time of day"
            )
            lower.contains("note") || lower.contains("journal") || lower.contains("diary") -> listOf(
                "Create and edit notes with rich title and body",
                "Pin important notes to the top",
                "Color code notes with modern pastel accents",
                "Organize notes with tags/folders",
                "Instant search across titles and note bodies",
                "Character and word count indicator",
                "Trash/archive system with restore capability",
                "1-tap copy note content to clipboard",
                "Export note as plain text or share",
                "Auto-save changes immediately while typing"
            )
            lower.contains("flashcard") || lower.contains("quiz") || lower.contains("vocab") -> listOf(
                "Create study decks with card count overview",
                "Add flashcards with Front (Question) and Back (Answer)",
                "Interactive 3D card flip animation",
                "Self-rating system (Easy, Medium, Hard)",
                "Study session score and completion summary",
                "Multiple choice quiz generation mode",
                "Shuffle cards for randomized recall testing",
                "Search terms across all decks",
                "Track card mastery level & streak",
                "Reset deck progress for a fresh review"
            )
            lower.contains("water") || lower.contains("hydration") -> listOf(
                "1-tap water log buttons (+250ml, +500ml, +750ml)",
                "Interactive animated water bottle/wave level",
                "Custom daily intake goal in ml/oz",
                "Hourly hydration reminder notifications",
                "Daily, weekly, and monthly hydration trends",
                "Quick edit or remove accidental logs",
                "Streak counter for reaching daily water goal",
                "Today's total vs goal percentage display"
            )
            lower.contains("task") || lower.contains("todo") || lower.contains("planner") -> listOf(
                "Add tasks with title, description, and priority",
                "Checkbox completion with smooth strike-through animation",
                "Due date and time selection",
                "Filter tabs: All, Today, Upcoming, Completed",
                "Priority badges (High, Medium, Low)",
                "Search tasks by title",
                "Organize tasks into custom project categories",
                "Clear completed tasks button",
                "Reorder tasks with drag-and-drop or priority sorting",
                "Overdue task visual highlight alert"
            )
            else -> cat.defaultFeatures
        }

        return specificFeatures
    }
}
