package ua.com.fleetwisor.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import ua.com.fleetwisor.R

class AgroswitIcon {

    val catalogOutlined: Painter
        @Composable
        get() = painterResource(id = R.drawable.catalog_outlined)
    val promotionalOffersOutlined: Painter
        @Composable
        get() = painterResource(id = R.drawable.promotional_offers_outlined)
    val schemeOfProtectionOutlined: Painter
        @Composable
        get() = painterResource(id = R.drawable.scheme_of_protection_outlined)
    val contactsOutlined: Painter
        @Composable
        get() = painterResource(id = R.drawable.contacts_outlined)
    val catalogFilled: Painter
        @Composable
        get() = painterResource(id = R.drawable.catalog_filled)
    val promotionalOffersFilled: Painter
        @Composable
        get() = painterResource(id = R.drawable.promotion_offers_filled)
    val schemeOfProtectionFilled: Painter
        @Composable
        get() = painterResource(id = R.drawable.scheme_of_protection_filled)
    val contactsFilled: Painter
        @Composable
        get() = painterResource(id = R.drawable.contacts_filled)

    val productPlaceholder: Painter
        @Composable
        get() = painterResource(R.drawable.product_placeholder)

    val socialFacebook: Painter
        @Composable
        get() = painterResource(id = R.drawable.social_facebook)
    val socialTelegram: Painter
        @Composable
        get() = painterResource(id = R.drawable.social_telegram)
    val socialViber: Painter
        @Composable
        get() = painterResource(id = R.drawable.social_viber)
    val socialInstagram: Painter
        @Composable
        get() = painterResource(id = R.drawable.social_instagram)
    val socialYoutube: Painter
        @Composable
        get() = painterResource(id = R.drawable.social_youtube)
    val search: Painter
        @Composable
        get() = painterResource(id = R.drawable.search)
    val filterUnSelected: Painter
        @Composable
        get() = painterResource(id = R.drawable.filter)
    val filterSelected: Painter
        @Composable
        get() = painterResource(id = R.drawable.filter_selected)
    val calculator: Painter
        @Composable
        get() = painterResource(id = R.drawable.calculator)
    val arrowUp: Painter
        @Composable
        get() = painterResource(id = R.drawable.arrow_drop_up)
    val arrowDown: Painter
        @Composable
        get() = painterResource(id = R.drawable.arrow_drop_down)
    val agroswitLogo: Painter
        @Composable
        get() = painterResource(id = R.drawable.agroswit_logo)
    val agroswitLogo2: Painter
        @Composable
        get() = painterResource(id = R.drawable.agroswit_logo2)
    val agroswitLogoFull: Painter
        @Composable
        get() = painterResource(id = R.drawable.agroswit_logo_full)
    val agroswitLogoText: Painter
        @Composable
        get() = painterResource(id = R.drawable.agroswit_logo_only_text)
    val arrowBack: Painter
        @Composable
        get() = painterResource(id = R.drawable.arrow_back)
    val hamburger: Painter
        @Composable
        get() = painterResource(id = R.drawable.agroswit_menu)
    val profile: Painter
        @Composable
        get() = painterResource(id = R.drawable.account_circle_filled)
    val arrowRight: Painter
        @Composable
        get() = painterResource(id = R.drawable.arrow_right)
    val sort: Painter
        @Composable
        get() = painterResource(id = R.drawable.sort)
    val networkError: Painter
        @Composable
        get() = painterResource(id = R.drawable.network_error)
    val bees: Painter
        @Composable
        get() = painterResource(id = R.drawable.bees)
    val door: Painter
        @Composable
        get() = painterResource(id = R.drawable.door)
    val order: Painter
        @Composable
        get() = painterResource(id = R.drawable.order)
    val password: Painter
        @Composable
        get() = painterResource(id = R.drawable.password)
    val logout: Painter
        @Composable
        get() = painterResource(id = R.drawable.logout)
    val person: Painter
        @Composable
        get() = painterResource(id = R.drawable.person)
    val email: Painter
        @Composable
        get() = painterResource(id = R.drawable.email)
    val phone: Painter
        @Composable
        get() = painterResource(id = R.drawable.phone)
    val company: Painter
        @Composable
        get() = painterResource(id = R.drawable.company)
    val code: Painter
        @Composable
        get() = painterResource(id = R.drawable.code)
    val personPin: Painter
        @Composable
        get() = painterResource(id = R.drawable.person_pin)
    val calendar: Painter
        @Composable
        get() = painterResource(id = R.drawable.calendar)
    val fop: Painter
        @Composable
        get() = painterResource(id = R.drawable.fop)

    val eyeOpen: Painter
        @Composable
        get() = painterResource(id = R.drawable.eye_open)

    val eyeClose: Painter
        @Composable
        get() = painterResource(id = R.drawable.eye_closed)

    val check: Painter
        @Composable
        get() = painterResource(id = R.drawable.check)

    val cross: Painter
        @Composable
        get() = painterResource(id = R.drawable.cross)

    val emailUnread: Painter
        @Composable
        get() = painterResource(id = R.drawable.email_unread)
    val screenRotation: Painter
        @Composable
        get() = painterResource(id = R.drawable.screen_rotation)
    val plus: Painter
        @Composable
        get() = painterResource(id = R.drawable.plus)
    val delete: Painter
        @Composable
        get() = painterResource(id = R.drawable.delete)
    val orderCreated: Painter
        @Composable
        get() = painterResource(id = R.drawable.order_created)
    val orderProceed: Painter
        @Composable
        get() = painterResource(id = R.drawable.order_proceed)
    val orderReviewed: Painter
        @Composable
        get() = painterResource(id = R.drawable.order_reviewed)
    val accepted: Painter
        @Composable
        get() = painterResource(id = R.drawable.order_accepted)
    val aborted: Painter
        @Composable
        get() = painterResource(id = R.drawable.order_aborted)
    val minus: Painter
        @Composable
        get() = painterResource(id = R.drawable.minus)
    val discount: Painter
        @Composable
        get() = painterResource(id = R.drawable.discount)
    val shoppingBag: Painter
        @Composable
        get() = painterResource(id = R.drawable.shopping_bag)
    val shoppingAgroswitBag: Painter
        @Composable
        get() = painterResource(id = R.drawable.shopping_agroswit_bag)
    val call: Painter
        @Composable
        get() = painterResource(id = R.drawable.call)
    val threeDot: Painter
        @Composable
        get() = painterResource(id = R.drawable.three_dot)
    val ellipse: Painter
        @Composable
        get() = painterResource(id = R.drawable.ellipse)
    val hourglass: Painter
        @Composable
        get() = painterResource(id = R.drawable.hourglass)
    val notification: Painter
        @Composable
        get() = painterResource(id = R.drawable.notifications)
    val noNotification: Painter
        @Composable
        get() = painterResource(id = R.drawable.no_notifications)
    val edit: Painter
        @Composable
        get() = painterResource(id = R.drawable.edit)
    val money: Painter
        @Composable
        get() = painterResource(id = R.drawable.money)
    val gasMeter: Painter
        @Composable
        get() = painterResource(id = R.drawable.gas_meter)
    val gasStation: Painter
        @Composable
        get() = painterResource(id = R.drawable.gas_station)
    val gasFluid: Painter
        @Composable
        get() = painterResource(id = R.drawable.opacity)
    val tool: Painter
        @Composable
        get() = painterResource(id = R.drawable.tool)
    val homeService: Painter
        @Composable
        get() = painterResource(id = R.drawable.home_service)
    val carRepair: Painter
        @Composable
        get() = painterResource(id = R.drawable.car_repair)
    val car: Painter
        @Composable
        get() = painterResource(id = R.drawable.car_filled)

}

internal val LocaleIcon = staticCompositionLocalOf { AgroswitIcon() }