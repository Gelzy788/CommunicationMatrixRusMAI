import type { ReactNode } from 'react'
import { Link, NavLink } from 'react-router'

import arrowIcon from '@/assets/arrow.svg'
import burgerIcon from '@/assets/burger.svg'
import clipboardIcon from '@/assets/clipboard.svg'
import crossIcon from '@/assets/cross.svg'
import heartIcon from '@/assets/heart.svg'
import homeIcon from '@/assets/home.svg'
import logoIcon from '@/assets/logo.svg'
import resultsIcon from '@/assets/results.svg'
import userIcon from '@/assets/user.svg'
import { Button } from '@/components/ui/button'
import { Sheet, SheetClose, SheetContent, SheetTitle, SheetTrigger } from '@/components/ui/sheet'
import { cn } from '@/lib/utils'

/** Основной компонент. Монтируются компоненты навигации для больших и маленьких 
экранов. Показывается лишь 1, в зависимости от размера экрана пользователя. */
export function Header() {
  // Заглушка до появления авторизации
  const isAuthenticated = true
  const userName = 'Анна Иванова'

  return (
    <header className="sticky top-0 z-10 border-b border-border bg-background lg:border-b-0">
      <div className="page-container flex h-(--header-height) items-center gap-10">
        <Link to="/" className="shrink-0">
          <img src={logoIcon} alt="Слова" width={111} height={32} />
        </Link>
        <DesktopNav isAuthenticated={isAuthenticated} userName={userName} />
        <MobileMenu isAuthenticated={isAuthenticated} userName={userName} />
      </div>
    </header>
  )
}

/** Общие пропсы для меню ПК и мобилки: что показать, решает только Header() */
type MenuProps = {
  isAuthenticated: boolean
  userName: string
}

// ---------- ПК (≥ lg) ----------

type NavItem = {
  to: string
  label: string
}

const guestDesktopNav: NavItem[] = [
  { to: '/about', label: 'О проекте' },
  { to: '/tests', label: 'Тестирования' },
  { to: '/', label: 'Как это работает' },
]

const authDesktopNav: NavItem[] = [
  { to: '/about', label: 'О проекте' },
  { to: '/tests', label: 'Тестирования' },
  { to: '/results', label: 'Мои результаты' },
]

/**
 * Навигация для ПК. На мобилке скрыта через `hidden lg:flex`, там вместо неё MobileMenu.
 */
function DesktopNav({ isAuthenticated, userName }: MenuProps) {
  const items = isAuthenticated ? authDesktopNav : guestDesktopNav

  return (
    <>
      <nav className="hidden items-center gap-7 lg:flex">
        {items.map((item) => (
          <NavLink
            key={item.label}
            to={item.to}
            className={({ isActive }) =>
              cn(
                'text-label text-muted-foreground transition-colors hover:text-foreground',
                isActive && 'text-foreground',
              )
            }
          >
            {item.label}
          </NavLink>
        ))}
      </nav>

      <div className="ml-auto hidden gap-6 lg:flex">
        {isAuthenticated ? (
          <Button variant="ghost" nativeButton={false} render={<Link to="/profile" />}>
            {userName}
          </Button>
        ) : (
          <>
            <Button variant="outline" nativeButton={false} render={<Link to="/login" />}>
              Войти
            </Button>
            <Button nativeButton={false} render={<Link to="/register" />}>
              Зарегистрироваться
            </Button>
          </>
        )}
      </div>
    </>
  )
}

// ---------- Мобильные экраны (< lg): бургер + полноэкранное меню ----------

type MobileNavItem = NavItem & {
  icon: ReactNode
}

function NavIcon({ src }: { src: string }) {
  return <img src={src} alt="" width={22} height={22} />
}

const authMobileNav: MobileNavItem[] = [
  { to: '/', label: 'Главная', icon: <NavIcon src={homeIcon} /> },
  { to: '/tests', label: 'Тестирования', icon: <NavIcon src={clipboardIcon} /> },
  { to: '/results', label: 'Мои результаты', icon: <NavIcon src={resultsIcon} /> },
  { to: '/profile', label: 'Мой профиль', icon: <NavIcon src={userIcon} /> },
  { to: '/about', label: 'О проекте', icon: <NavIcon src={heartIcon} /> },
]

const guestMobileNav: MobileNavItem[] = [
  { to: '/', label: 'Главная', icon: <NavIcon src={homeIcon} /> },
  { to: '/tests', label: 'Тестирования', icon: <NavIcon src={clipboardIcon} /> },
  { to: '/about', label: 'О проекте', icon: <NavIcon src={heartIcon} /> },
]

/**
 * Бургер и полноэкранное меню для мобилки (на ПК скрыто через `lg:hidden`).
 * Каждая ссылка обёрнута в SheetClose, чтобы меню закрывалось при переходе.
 */
function MobileMenu({ isAuthenticated, userName }: MenuProps) {
  const items = isAuthenticated ? authMobileNav : guestMobileNav

  return (
    <Sheet>
      <SheetTrigger
        render={
          <Button
            variant="ghost"
            size="icon"
            className="ml-auto lg:hidden"
            aria-label="Открыть меню"
          />
        }
      >
        <img src={burgerIcon} alt="" width={28} height={28} />
      </SheetTrigger>

      <SheetContent
        side="top"
        showCloseButton={false}
        className="max-h-dvh gap-0 overflow-y-auto bg-background"
      >
        {/* Шапка внутри меню повторяет обычную: лого + крестик на месте бургера */}
        <div className="page-container flex h-(--header-height) shrink-0 items-center">
          <SheetClose nativeButton={false} render={<Link to="/" className="shrink-0" />}>
            <img src={logoIcon} alt="Слова" width={111} height={32} />
          </SheetClose>
          <SheetClose
            render={
              <Button variant="ghost" size="icon" className="ml-auto" aria-label="Закрыть меню" />
            }
          >
            <img src={crossIcon} alt="" width={24} height={24} />
          </SheetClose>
        </div>

        <SheetTitle className="sr-only">Меню</SheetTitle>

        <div className="page-container flex flex-col gap-6 pt-2 pb-6">
          {isAuthenticated && (
            <SheetClose
              nativeButton={false}
              render={
                <Link
                  to="/profile"
                  className="flex flex-col gap-1 rounded-xl bg-secondary p-(--card-padding) ring-1 ring-border"
                />
              }
            >
              <span className="text-h4 text-foreground">{userName}</span>
              <span className="text-small text-muted-foreground">Личный кабинет</span>
            </SheetClose>
          )}

          <nav className="flex flex-col">
            {items.map((item) => (
              <SheetClose
                key={item.label}
                nativeButton={false}
                render={
                  <Link
                    to={item.to}
                    className="flex min-h-11 items-center gap-3 text-body font-medium text-foreground"
                  />
                }
              >
                {item.icon}
                <span className="flex-1">{item.label}</span>
                <img src={arrowIcon} alt="" width={20} height={20} />
              </SheetClose>
            ))}
          </nav>

          <div className="flex flex-col gap-6">
            {isAuthenticated ? (
              <SheetClose render={<Button variant="outline" className="w-full" />}>
                Выйти из аккаунта
              </SheetClose>
            ) : (
              <div className="flex flex-col gap-3">
                <SheetClose
                  nativeButton={false}
                  render={
                    <Button
                      variant="outline"
                      className="w-full"
                      nativeButton={false}
                      render={<Link to="/login" />}
                    />
                  }
                >
                  Войти
                </SheetClose>
                <SheetClose
                  nativeButton={false}
                  render={
                    <Button
                      className="w-full"
                      nativeButton={false}
                      render={<Link to="/register" />}
                    />
                  }
                >
                  Зарегистрироваться
                </SheetClose>
              </div>
            )}
            <SheetClose
              nativeButton={false}
              render={<Link to="/privacy" className="self-start text-label link" />}
            >
              Политика конфиденциальности
            </SheetClose>
          </div>
        </div>
      </SheetContent>
    </Sheet>
  )
}
